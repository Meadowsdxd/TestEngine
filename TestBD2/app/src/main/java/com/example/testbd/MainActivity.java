package com.example.testbd;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static android.R.layout.simple_list_item_1;


public class MainActivity extends AppCompatActivity {
   List<Formula> form;
    EditText edI1, edI2, edI3,names,edI4,edI5,edI6,edI7,edI8;
    private DatabaseReference mDataBase;
    private String FORMULI="items";

   // private List<Formula> form;
    public ArrayAdapter<Formula> adapter;
    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> pairedDeviceArrayList;
    private int index=0;
    public UUID myUUID;
    ThreadConnectBTdevice myThreadConnectBTdevice;
    ThreadConnected myThreadConnected;
    private StringBuilder sb = new StringBuilder();
    ListView listViewPairedDevice;
    ArrayAdapter<String> pairedDeviceAdapter;
    final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administaroract);
    init();
    }
    public void init(){
        listViewPairedDevice = (ListView)findViewById(R.id.pairedlist);
        names=(EditText) findViewById(R.id.name);
        edI1=(EditText) findViewById(R.id.Kvt);
        edI2=(EditText) findViewById(R.id.Oborot);
        edI3=(EditText) findViewById(R.id.i1);
        edI4=(EditText) findViewById(R.id.KPD);
        edI5=(EditText) findViewById(R.id.KOEF);
        edI6=(EditText) findViewById(R.id.In);
        edI7=(EditText) findViewById(R.id.Om);
        edI8=(EditText) findViewById(R.id.Old);
        mDataBase=FirebaseDatabase.getInstance().getReference(FORMULI);
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this hardware platform", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        String stInfo = bluetoothAdapter.getName() + " " + bluetoothAdapter.getAddress();
        // textInfo.setText(String.format("?????? ????????????????????: %s", stInfo));
        form = new ArrayList<Formula>();

    }
    static String key;
    public void onClickSave(View view){
        Check check = new Check();
        if (check.ISNull(String.valueOf(names.getText()), String.valueOf(edI1.getText()),
                String.valueOf(edI2.getText()), String.valueOf(edI3.getText()),String.valueOf(edI4.getText()), String.valueOf(edI5.getText()),
                String.valueOf(edI6.getText()), String.valueOf(edI7.getText()),String.valueOf(edI8.getText()),
                this)){
            try{
    String id=mDataBase.getKey();
        String name= String.valueOf(names.getText());
                String KVT= String.valueOf(edI1.getText());
                String Oborot= String.valueOf(edI2.getText());
                String i= String.valueOf(edI3.getText());
                String KPD= String.valueOf(edI4.getText());
                String KOEF= String.valueOf(edI5.getText());
                String In= String.valueOf(edI6.getText());
                String Om= String.valueOf(edI7.getText());
                String Old= String.valueOf(edI8.getText());

    Formula formula=new Formula(id,name,KVT,Oborot,i,KPD,KOEF,In,Om,Old);

                Toast.makeText(this, "put in DataBase", Toast.LENGTH_LONG).show();
//            adapter.notifyDataSetChanged();
    mDataBase.push().setValue(formula);
                key = mDataBase.child("items").push().getKey();


            }catch (NumberFormatException e){e.printStackTrace();
            Toast.makeText(this, "\tERROR\nCan`t put in DataBase", Toast.LENGTH_LONG).show();
    }
            edI1.setText("");edI2.setText("");edI3.setText("");edI4.setText("");edI5.setText("");edI6.setText("");edI7.setText("");edI8.setText("");}}


    public   void next(View view){
        Intent i=new Intent(MainActivity.this,DeleteActivity.class);
        startActivity(i);
    }
    public void onClickRead(View view){
Intent i=new Intent(MainActivity.this,ReadActivity.class);
startActivity(i);
    }

    @Override
    protected void onStart() { // ???????????? ???? ?????????????????? Bluetooth
        super.onStart();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
//        setup();

    }
    private void setup() { // ???????????????? ???????????? ?????????????????????? Bluetooth-??????????????????
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) { // ???????? ???????? ?????????????????????? ????????????????????
            pairedDeviceArrayList = new ArrayList<>();
            for (BluetoothDevice device : pairedDevices) { // ?????????????????? ?????????????????????? ???????????????????? - ?????? + MAC-????????????

                pairedDeviceArrayList.add(device.getName() + "\n" + device.getAddress());
            }

            pairedDeviceAdapter = new ArrayAdapter<>(this, simple_list_item_1, pairedDeviceArrayList);
         listViewPairedDevice.setAdapter(pairedDeviceAdapter);
           listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() { // ???????? ???? ?????????????? ????????????????????

                @Override
                public void onItemClick(AdapterView< ?> parent, View view, int position, long id) { //?????? ???????????? ?????????? ???????????? !!!!
                    listViewPairedDevice.setVisibility(View.GONE); // ?????????? ?????????? ???????????????? ????????????
                    String  itemValue = (String) listViewPairedDevice.getItemAtPosition(position);
                    String MAC = itemValue.substring(itemValue.length() - 17); // ?????????????????? MAC-??????????
                    BluetoothDevice device2 = bluetoothAdapter.getRemoteDevice(MAC);
                    myThreadConnectBTdevice = new ThreadConnectBTdevice(device2);
                    myThreadConnectBTdevice.start();  // ?????????????????? ?????????? ?????? ?????????????????????? Bluetooth
                }
            });
        }
    }
        private class ThreadConnectBTdevice extends Thread { // ?????????? ?????? ???????????????? ?? Bluetooth

            private BluetoothSocket bluetoothSocket = null;

            public ThreadConnectBTdevice(BluetoothDevice device) {

                try {

                    bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void run() { // ??????????????

                boolean success = false;

                try {
                    bluetoothSocket.connect();
                    success = true;
                }

                catch (IOException e) {
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "?????? ????????????????, ?????????????????? Bluetooth-???????????????????? ?? ?????????????? ???????????? ??????????????????!", Toast.LENGTH_LONG).show();
                            listViewPairedDevice.setVisibility(View.VISIBLE);
                        }
                    });

                    try {
                        bluetoothSocket.close();
                    }

                    catch (IOException e1) {

                        e1.printStackTrace();
                    }
                }

                if(success) {  // ???????? ????????????????????????????, ?????????? ?????????????????? ???????????? ?? ???????????????? ?? ?????????????????? ?????????? ???????????? ?? ???????????????? ????????????

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
//                    ButPanel.setVisibility(View.VISIBLE); // ?????????????????? ???????????? ?? ????????????????
                        }
                    });

                    myThreadConnected = new ThreadConnected(bluetoothSocket);
                    myThreadConnected.start(); // ???????????? ???????????? ???????????? ?? ???????????????? ????????????
                }
            }


            public void cancel() {

                Toast.makeText(getApplicationContext(), "Close - BluetoothSocket", Toast.LENGTH_LONG).show();

                try {
                    bluetoothSocket.close();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } // END ThreadConnectBTdevice:


        private class ThreadConnected extends Thread {    // ?????????? - ?????????? ?? ???????????????? ????????????

            private final InputStream connectedInputStream;
            private final OutputStream connectedOutputStream;

            private String sbprint;

            public ThreadConnected(BluetoothSocket socket) {

                InputStream in = null;
                OutputStream out = null;

                try {
                    in = socket.getInputStream();
                    out = socket.getOutputStream();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                connectedInputStream = in;
                connectedOutputStream = out;
            }


            @Override
            public void run() { // ?????????? ????????????

                while (true) {
                    try {
                        byte[] buffer = new byte[1];
                        int bytes = connectedInputStream.read(buffer);
                        String strIncom = new String(buffer, 0, bytes);
                        sb.append(strIncom); // ???????????????? ?????????????? ?? ????????????
                        int endOfLineIndex = sb.indexOf("\r\n"); // ???????????????????? ?????????? ????????????

                        if (endOfLineIndex > 0) {

                            sbprint = sb.substring(0, endOfLineIndex);
                            sb.delete(0, sb.length());

                            runOnUiThread(new Runnable() { // ?????????? ????????????

                                @Override
                                public void run() {

                                    switch (index) {

                                        case 1:
                                            edI1.setText(sbprint);
                                            break;

                                        case 2:
                                            edI2.setText(sbprint);
                                            break;

                                        case 3:
                                            edI3.setText(sbprint);
                                            break;

                                        default:
                                            break;
                                    }
                                }
                            });
                        }
                    } catch (IOException e) {
                        break;
                    }
                }
            }


            public void write(byte[] buffer) {
                try {
                    connectedOutputStream.write(buffer);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

/////////////////// ?????????????? ???????????? /////////////////////

        public void clickInfo(View v){

            if(myThreadConnected!=null) {
                if (index==3) index=0; // ?????? ???????? ?????? ???? ???? ?????????????????? ???????????? ?????? ???? ??????????????
                index++;
                switch (index){
                    case 1:
                    {
                        byte[] bytesToSend = "a".getBytes();
                        myThreadConnected.write(bytesToSend );
                        break;
                    }
                    case 2:
                    {
                        byte[] bytesToSend = "b".getBytes();
                        myThreadConnected.write(bytesToSend );
                        break;
                    }
                    case 3:
                    {
                        byte[] bytesToSend = "c".getBytes();
                        myThreadConnected.write(bytesToSend );
                        break;
                    }
                    default: break;
                }

            }
        }


    }


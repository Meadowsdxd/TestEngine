package com.example.testbd;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static android.R.layout.simple_list_item_1;

public class ShowActivity extends AppCompatActivity {
private TextView otvet1,otvet2,otvet3,Otvet1Last,Otvet2Last,Otvet3Last;
EditText edt1Show,edt2Show,edt3Show,nameShow;
    TextView textView;
    Button bv;
    private DatabaseReference mDataBase;
    private String FORMULI="items";
Button takeShowed, readShowed;
    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> pairedDeviceArrayListShow;
    private int index=0;
    public UUID myUUID;
ThreadConnectBTdevice myThreadConnectBTdevice;
ThreadConnected myThreadConnected;
    private StringBuilder sb = new StringBuilder();
    ListView listViewPairedDevice;
    ArrayAdapter<String> pairedDeviceAdapter;
    final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";
    ImageView imageView,imageView2,imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
        getIntentMain();
    }
Button more;
    private void init(){
        listViewPairedDevice = (ListView)findViewById(R.id.pairedlistShow);
        otvet1=findViewById(R.id.otvet1);
        otvet2=findViewById(R.id.otvet2);
        Otvet1Last=findViewById(R.id.OtvetLast1);
        Otvet2Last=findViewById(R.id.OtvetLast2);
        Otvet3Last=findViewById(R.id.OtvetLast3);
        edt1Show=findViewById(R.id.i1Show);
        edt2Show=findViewById(R.id.i2Show);
        edt3Show=findViewById(R.id.i3Show);
        nameShow=findViewById(R.id.nameShow);
        takeShowed=findViewById(R.id.takeShow);
        textView  =findViewById(R.id.textView2);
        more=findViewById(R.id.More);
        more.setVisibility(View.GONE);
        readShowed=findViewById(R.id.readShow);
         imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView3);
        imageView3= findViewById(R.id.imageView4);
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);
        otvet1.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        Otvet1Last.setVisibility(View.GONE);
        Otvet2Last.setVisibility(View.GONE);
        Otvet3Last.setVisibility(View.GONE);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this hardware platform", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        String stInfo = bluetoothAdapter.getName() + " " + bluetoothAdapter.getAddress();
        // textInfo.setText(String.format("Это устройство: %s", stInfo));
    }
    String saked,saked2,saked3;
    public void onClickMore(View view){
        String w1 = String.format("%.2f", Double.parseDouble(I7)-(Double.parseDouble(String.valueOf(x1))));// 7,001
        String w1t = String.format("%.2f", ((Double.parseDouble(I7)-(Double.parseDouble(String.valueOf(x1))))*100)/Double.parseDouble(I7));
        String w2 = String.format("%.2f", Double.parseDouble(I7)-(Double.parseDouble(String.valueOf(x2))));// 7,001
        String w2t = String.format("%.2f", ((Double.parseDouble(I7)-(Double.parseDouble(String.valueOf(x2))))*100)/Double.parseDouble(I7));
        String w3 = String.format("%.2f", Double.parseDouble(I7)-(Double.parseDouble(String.valueOf(x3))));// 7,001
        String w3t = String.format("%.2f", ((Double.parseDouble(I7)-(Double.parseDouble(String.valueOf(x3))))*100)/Double.parseDouble(I7));
        if ((l < Double.parseDouble(String.valueOf((x1)))) && (Double.parseDouble(String.valueOf((x1))) < k)) {
            saked=getString(R.string.Action_True)+w1+" "+getString(R.string.Action_Or)+w1t+" %";
        } else {
            saked=getString(R.string.Action_False)+w1+" "+getString(R.string.Action_Or)+w1t+" %";
        }
        if (l < Double.parseDouble(String.valueOf(x2)) && Double.parseDouble(String.valueOf(x2)) < k) {

            saked2=getString(R.string.Action_True)+w2+" "+getString(R.string.Action_Or)+w2t+" %";
        } else {
            saked2=getString(R.string.Action_False)+w2+" "+getString(R.string.Action_Or)+w2t+" %";
        }
        if (l < Double.parseDouble(String.valueOf(x3)) && Double.parseDouble(String.valueOf(x3)) < k) {

            saked3=getString(R.string.Action_True)+w3+" "+getString(R.string.Action_Or)+w3t+" %";
        } else {
            saked3=getString(R.string.Action_False)+w3+" "+getString(R.string.Action_Or)+w3t+" %";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.Action_Dop)).setMessage(
                "\n----------------------------------------------------------\n"+
                        getString(R.string.Action_First)+saked+ "\n----------------------------------------------------------\n"+
                        getString(R.string.Action_Second)+saked2+ "\n----------------------------------------------------------\n"+
                        getString(R.string.Action_Third)+saked3+ "\n----------------------------------------------------------\n"
                );
        builder.create();
        builder.show();



    }
        String x2t,x1t,x3t;
    Double k,l,x1,x2,x3;
    public void onClickRead(View view){   Check check = new Check();
        if (check.ISNul(String.valueOf(edt1Show.getText()), String.valueOf(edt2Show.getText()),
                String.valueOf(edt3Show.getText()),
                this)){
            try {
        listViewPairedDevice.setVisibility(View.GONE);
    edt1Show.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                more.setVisibility(View.VISIBLE);
        edt2Show.setVisibility(View.GONE);
        edt3Show.setVisibility(View.GONE);
        takeShowed.setVisibility(View.GONE);
        readShowed.setVisibility(View.GONE);
        nameShow.setVisibility(View.GONE);
        otvet1.setVisibility(View.VISIBLE);
        otvet1.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.VISIBLE);
                Otvet1Last.setVisibility(View.VISIBLE);
                Otvet2Last.setVisibility(View.VISIBLE);
                        Otvet3Last.setVisibility(View.VISIBLE);

                 l = Double.parseDouble(I7) - Double.parseDouble(I7) * (Double.parseDouble(I6) / 100);
                 k = Double.parseDouble(I7) + Double.parseDouble(I7) * (Double.parseDouble(I6) / 100);

                double value1 = 380 / Double.parseDouble(String.valueOf(edt1Show.getText()));
                double value2 = 380 / Double.parseDouble(String.valueOf(edt2Show.getText()));
                double value3 = 380 / Double.parseDouble(String.valueOf(edt3Show.getText()));
                 x1 = Math.sqrt(Math.pow(380, 2) / Math.pow(Double.parseDouble(String.valueOf(edt1Show.getText())), 2) - value1);
                 x2 = Math.sqrt(Math.pow(380, 2) / Math.pow(Double.parseDouble(String.valueOf(edt2Show.getText())), 2) - value2);
                 x3 = Math.sqrt(Math.pow(380, 2) / Math.pow(Double.parseDouble(String.valueOf(edt3Show.getText())), 2) - value3);
                String value1t = String.format("%.2f", value1);// 7,000
                String value2t = String.format("%.2f", value2);// 7,001
                String value3t = String.format("%.2f", value3);// 7,001
                 x1t = String.format("%.2f", x1);// 7,000
                 x2t = String.format("%.2f", x2);// 7,001
                 x3t = String.format("%.2f", x3);// 7,001

                if ((l < Double.parseDouble(String.valueOf((x1)))) && (Double.parseDouble(String.valueOf((x1))) < k)) {
                    imageView.setImageResource(R.drawable.green);
                } else {
                    imageView.setImageResource(R.drawable.red);
                }
                if (l < Double.parseDouble(String.valueOf(x2)) && Double.parseDouble(String.valueOf(x2)) < k) {

                    imageView2.setImageResource(R.drawable.green);
                } else {
                    imageView2.setImageResource(R.drawable.red);
                }
                if (l < Double.parseDouble(String.valueOf(x3)) && Double.parseDouble(String.valueOf(x3)) < k) {

                    imageView3.setImageResource(R.drawable.green);
                } else {
                    imageView3.setImageResource(R.drawable.red);
                }

                Otvet1Last.setText(x1t + " / " + I7 + " Om");
                Otvet2Last.setText(x2t + " / " + I7 + " Om");
                Otvet3Last.setText(x3t + " / " + I7 + " Om");

            } catch (NumberFormatException e){e.printStackTrace();
            Toast.makeText(this, "\tERROR\nCan`t take date", Toast.LENGTH_LONG).show();
        }
        }}
    String I1,I2,I3,I4,I5,I6,I7,I8,Name;
    String xt;
    double x;
    private void getIntentMain(){
        Intent i=getIntent();
        if(i!=null){
            Name=  i.getStringExtra("name");
            I1=  i.getStringExtra("i1");
             I2=  i.getStringExtra("i2");
             I3=  i.getStringExtra("i3");
            I4=  i.getStringExtra("i4");
             I5=  i.getStringExtra("i5");
             I6=  i.getStringExtra("i6");
             I7=  i.getStringExtra("i7");
            I8=  i.getStringExtra("i8");
            /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
            double value=380/Double.parseDouble(String.valueOf(I3));

            /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
           x =Math.sqrt(Math.pow(380,2)/Math.pow(Double.parseDouble(I3),2)-value);
            String valuet = String.format("%.2f",value);
            xt = String.format("%.2f",x);

            nameShow.setText(Name);

            otvet1.setText(
                    (Name)+"\n"+
                            getString(R.string.Action_Sila) +(I3)+"\n"+
                            getString(R.string.Action_Om)+valuet+"\n"+
                            getString(R.string.Action_OmOb)+xt+"\n"+
                            getString(R.string.Action_KVT)+I1+"\n"+
                            getString(R.string.Action_Oborot)+I2+"\n"+
                            getString(R.string.Action_KKD)+I4+"\n"+
                            getString(R.string.Action_KOEF)+I5+"\n"+
                            getString(R.string.Action_Luft)+I6+"\n"+
                            getString(R.string.Action_OmNorm)+I7+"\n"+
                            getString(R.string.Action_OLD)+I8);

        }

    }
    @Override
    protected void onStart() { // Запрос на включение Bluetooth
        super.onStart();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
        setup();

    }
    private void setup() { // Создание списка сопряжённых Bluetooth-устройств
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) { // Если есть сопряжённые устройства
            pairedDeviceArrayListShow = new ArrayList<>();
            for (BluetoothDevice device : pairedDevices) { // Добавляем сопряжённые устройства - Имя + MAC-адресс

                pairedDeviceArrayListShow.add(device.getName() + "\n" + device.getAddress());
            }

            pairedDeviceAdapter = new ArrayAdapter<>(this, simple_list_item_1, pairedDeviceArrayListShow);
            listViewPairedDevice.setAdapter(pairedDeviceAdapter);
            listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() { // Клик по нужному устройству

                @Override
                public void onItemClick(AdapterView< ?> parent, View view, int position, long id) { //тут пробел после скобки !!!!
                    listViewPairedDevice.setVisibility(View.GONE); // После клика скрываем список
                    String  itemValue = (String) listViewPairedDevice.getItemAtPosition(position);
                    String MAC = itemValue.substring(itemValue.length() - 17); // Вычленяем MAC-адрес
                    BluetoothDevice device2 = bluetoothAdapter.getRemoteDevice(MAC);
                    myThreadConnectBTdevice = new ThreadConnectBTdevice(device2);
                    myThreadConnectBTdevice.start();  // Запускаем поток для подключения Bluetooth
                }
            });
        }
    }
    private class ThreadConnectBTdevice extends Thread  {
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
        public void run() { // Коннект

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
                        Toast.makeText(ShowActivity.this, "Нет коннекта, проверьте Bluetooth-устройство с которым хотите соединица!", Toast.LENGTH_LONG).show();
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

            if(success) {  // Если законнектились, тогда открываем панель с кнопками и запускаем поток приёма и отправки данных

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
//                    ButPanel.setVisibility(View.VISIBLE); // открываем панель с кнопками
                    }
                });

                myThreadConnected = new ThreadConnected(bluetoothSocket);
                myThreadConnected.start(); // запуск потока приёма и отправки данных
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

    }

    private class ThreadConnected extends Thread{
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
        public void run() { // Приём данных

            while (true) {
                try {
                    byte[] buffer = new byte[1];
                    int bytes = connectedInputStream.read(buffer);
                    String strIncom = new String(buffer, 0, bytes);
                    sb.append(strIncom); // собираем символы в строку
                    int endOfLineIndex = sb.indexOf("\r\n"); // определяем конец строки

                    if (endOfLineIndex > 0) {

                        sbprint = sb.substring(0, endOfLineIndex);
                        sb.delete(0, sb.length());

                        runOnUiThread(new Runnable() { // Вывод данных

                            @Override
                            public void run() {

                                switch (index) {

                                    case 1:
                                        edt1Show.setText(sbprint);
                                        break;

                                    case 2:
                                        edt2Show.setText(sbprint);
                                        break;

                                    case 3:
                                        edt3Show.setText(sbprint);
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

/////////////////// Нажатие кнопки /////////////////////

    public void clickInfo(View v){

        if(myThreadConnected!=null) {
            if (index==3) index=0; // для того что бы не сбивались данные при их запросе
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

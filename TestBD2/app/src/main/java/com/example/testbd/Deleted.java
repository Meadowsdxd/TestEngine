package com.example.testbd;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Deleted extends AppCompatActivity {
    private TextView otvet1,otvet2,otvet3,Otvet1Last,Otvet2Last,Otvet3Last;
    EditText edt1Show,edt2Show,edt3Show,nameShow;
    private DatabaseReference mDataBase;
    private String FORMULI="items";
    Button takeShowed, readShowed;
    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> pairedDeviceArrayListShow;
    private int index=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted);


        getIntentMain();

    }


    public void onClickRead(View view){   Check check = new Check();
        DatabaseReference myRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
      myRef = database.getReference(FORMULI);

        Query applesQuery = myRef.orderByChild("name").equalTo(Name);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Intent i=new Intent(Deleted.this,DeleteActivity.class);
        startActivity(i);
    }

    static String key;
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




    }}

}

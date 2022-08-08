package com.example.testbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class administaroract extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> listData;
    private List<Formula> listTemp;
    DatabaseReference myRef;
    private String FORMULI="items";
    Spinner r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administaroract);
        init();

        setOnClickItem();

    }

    private void init(){
        listView=findViewById(R.id.list);
        listData=new ArrayList<>();
        listTemp=new ArrayList<>();



        listView.setAdapter(arrayAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FORMULI);

    }




    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Formula formula=listTemp.get(position);
                Intent i=new Intent(administaroract.this,ShowActivity.class);
               /* i.putExtra("i1",formula.i);
                i.putExtra("i2",formula.);
                i.putExtra("i3",formula.i3);*/
                startActivity(i);
            }
        });
    }
    public void Add(View view){
/*    Intent i=new Intent(ReadActivity.this,MainActivity.class);
    startActivity(i);*/
      }}
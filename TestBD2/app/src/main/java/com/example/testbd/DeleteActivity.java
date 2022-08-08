package com.example.testbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    public static ListView listView;
    public ArrayAdapter<Formula> adapter;
    private List<String> listData;
    private List<Formula> listTemp;
    DatabaseReference myRef;
    private String FORMULI="items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        init();
    }
    private void init(){
        listView=findViewById(R.id.list);
        listData=new ArrayList<>();
        listTemp=new ArrayList<>();
        Button button=findViewById(R.id.button);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTemp);
        listView.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FORMULI);
        getDataFromDB();
        setOnClickItem();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DeleteActivity.this,ReadActivity.class);
                startActivity(i);
            }
        });

    }

    private void getDataFromDB(){

        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size()>0)listData.clear();
                if(listTemp.size()>0)listData.clear();

                for(DataSnapshot ds : snapshot.getChildren()){
                    Formula formula=ds.getValue(Formula.class);
                    transletor tr=ds.getValue(transletor.class);
                    assert formula!=null;
                    String name,i1;
                    listData.add(formula.name);

                    listTemp.add(formula);


                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.addValueEventListener(eventListener);
    }



    private DatabaseReference mDataBase;
    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Formula formula=listTemp.get(position);
                Intent i=new Intent(DeleteActivity.this,Deleted.class);
                i.putExtra("name",formula.name);
                i.putExtra("i1",formula.KVT);
                i.putExtra("i2",formula.Oborot);
                i.putExtra("i3",formula.i);
                i.putExtra("i4",formula.KPD);
                i.putExtra("i5",formula.KOEF);
                i.putExtra("i6",formula.In);
                i.putExtra("i7",formula.Om);
                i.putExtra("i8",formula.Old);
                startActivity(i);
            }
        });
    }}


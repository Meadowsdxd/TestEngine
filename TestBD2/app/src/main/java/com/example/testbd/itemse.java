package com.example.testbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class itemse extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> listData;
    private List<Formula> listTemp;
    DatabaseReference myRef;
    private String FORMULI="items";

    public TextView textView;
    Spinner r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemse);
        init();
        getDataFromDB();


    }

    private void init(){
        listView=findViewById(R.id.list);
        listData=new ArrayList<>();
        listTemp=new ArrayList<>();

          textView=(TextView) findViewById(R.id.textView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FORMULI);

    }
    private void getDataFromDB(){
        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size()>0)listData.clear();
                if(listTemp.size()>0)listData.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Formula formula=ds.getValue(Formula.class);
                    assert formula!=null;
                    textView.setText(formula.name);
                    listTemp.add(formula);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addValueEventListener(eventListener);
    }}
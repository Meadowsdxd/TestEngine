package com.example.testbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class AdminActivity extends AppCompatActivity {
    private String Admin="Admin";
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> listData;
    private List<Administrator> listTemp;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        try{
        FirebaseDatabase database = FirebaseDatabase.getInstance();

    /*    String name= ("admin");
        String pass= ("1234");
        Administrator formula=new Administrator(name,pass);
        myRef.push().setValue(formula);*/
    }catch (NumberFormatException e){e.printStackTrace();
        Toast.makeText(this, "\tERROR\nCan`t put in DataBase", Toast.LENGTH_LONG).show();
    }
        init();
        getDataFromDB();
        }
        EditText em,ps;
    String email;
    String pass;
    private void init(){
        listView=findViewById(R.id.listTest);
        listView.setVisibility(View.INVISIBLE);
        em=(EditText) findViewById(R.id.Email);
        ps=(EditText) findViewById(R.id.Password);
        listData=new ArrayList<>();
        listTemp=new ArrayList<>();
       arrayAdapter=new ArrayAdapter<>(this, simple_list_item_1,listData);
        listView.setAdapter(arrayAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Admin);


    }

    private void getDataFromDB(){
        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size()>0)listData.clear();
                if(listTemp.size()>0)listData.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Administrator administrator=ds.getValue(Administrator.class);
                    assert administrator!=null;
                    listData.add(administrator.name);
                    listData.add(administrator.pass);
                    email=(administrator.name);
                   pass=(administrator.pass);
                    listTemp.add(administrator);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addValueEventListener(eventListener);
    }

    public void In(View view){

    if ((em.getText().toString().isEmpty() )&&( ps.getText().toString().isEmpty())) {   Toast.makeText(this, R.string.Action_check, Toast.LENGTH_LONG).show();

    } else {
        try {
            if ((email.equals((em.getText()).toString())) && (pass.equals((ps.getText()).toString()))) {

                Intent i = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, R.string.Action_check, Toast.LENGTH_SHORT).show();
                // Toast.makeText(this, ps.getText(), Toast.LENGTH_SHORT).show();}
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.Action_check, Toast.LENGTH_LONG).show();

        }
    }

}}


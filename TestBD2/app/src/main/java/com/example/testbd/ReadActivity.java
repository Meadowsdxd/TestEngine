package com.example.testbd;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {


    public static  ListView listView;
    public ArrayAdapter<Formula> adapter;
    public ArrayAdapter<transletor> adapter2;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> listData;

    private List<Formula> listTemp;
    private List<Formula> form;
    DatabaseReference myRef;
    private String FORMULI="items";
    private List<Formula> forme;
    Spinner r1;
    Formula user = new Formula();
    final static String FileName="Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();


    }

    private void init(){
        listView=findViewById(R.id.list);
        listData=new ArrayList<>();
        listTemp=new ArrayList<>();


           adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTemp);
            listView.setAdapter(adapter);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference(FORMULI);
           getDataFromDB();
           setOnClickItem();


        }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else { return true; }}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.one:
                Intent i=new Intent(ReadActivity.this,AdminActivity.class);
                startActivity(i);
                return true;
            case R.id.two:
                Intent i2=new Intent(ReadActivity.this,pager.class);
                startActivity(i2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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

private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Formula formula=listTemp.get(position);
                Intent i=new Intent(ReadActivity.this,ShowActivity.class);
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
}

}



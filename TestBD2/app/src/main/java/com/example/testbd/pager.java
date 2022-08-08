package com.example.testbd;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class pager extends AppCompatActivity implements View.OnClickListener  {
    private ViewPager pageer;
    private PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        pageer=findViewById(R.id.pager);
        List<Fragment> list=new ArrayList<>();
        list.add(new AbouMe());
        list.add(new AboutProg());
        adapter=new Slider(getSupportFragmentManager(),list);
        pageer.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}
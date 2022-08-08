package com.example.testbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StartActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ImageView sunImageView = (ImageView) findViewById(R.id.imageView2);
        // Анимация для восхода солнца
        Animation AnimationView = AnimationUtils.loadAnimation(this, R.anim.run);
        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(AnimationView);
        Button right = (Button) findViewById(R.id.User);
        // Анимация для восхода солнца
        Animation AnimationRight = AnimationUtils.loadAnimation(this, R.anim.runb);
        // Подключаем анимацию к нужному View
        right.startAnimation(AnimationRight);
        Button left = (Button) findViewById(R.id.Admin);
        // Анимация для восхода солнца
        Animation AnimationLeft = AnimationUtils.loadAnimation(this, R.anim.runa);
        // Подключаем анимацию к нужному View
        left.startAnimation(AnimationLeft);
    }
    public void Right(View view){
        Intent i=new Intent(StartActivity.this,ReadActivity.class);
        startActivity(i);
    }
    public void Left(View view){
        Intent i=new Intent(StartActivity.this,AdminActivity.class);
        startActivity(i);
    }
}
package com.example.testbd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AboutProg extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_prog, container, false);
        TextView textView=(TextView) view.findViewById(R.id.aboutProg);
        textView.setText("Multimetr 1.0\n"+
                "Version 1.0\n"+
                "Android 9.0\n"+
                "Date 13.06.2021\n"+
                "Програмное обеспечение создано на\nзаказ Горшкову Виктору Викторовичу\n");
        return view;
    }
}
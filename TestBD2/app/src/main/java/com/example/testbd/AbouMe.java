package com.example.testbd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AbouMe extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_abou_me, container, false);
        TextView textView=(TextView) view.findViewById(R.id.aboutMe);
        textView.setText("Разработчик: Рева Ярослав Евгениевич\n"+
                "Студент: 4 курса\n"+
                "Номер телефона: +3800983580772\n"+
                "Email: egouscz@gmail.com\n");
        return view;
    }

}
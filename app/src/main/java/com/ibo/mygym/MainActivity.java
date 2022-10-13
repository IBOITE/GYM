package com.ibo.mygym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Controller.DataBaseHandler;
import Controller.ListAdapter;
import Model.Person;

public class MainActivity extends AppCompatActivity {
    DataBaseHandler db;
    RecyclerView recyclerView;
    ListAdapter adapter;
    FloatingActionButton addBtn;
    TextView tvSerach,tvNumPerson;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DataBaseHandler(this);
        recyclerView=findViewById(R.id.reID);
        adapter=new ListAdapter(db.getAll(),this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        addBtn=findViewById(R.id.fabAdd);
        tvNumPerson=findViewById(R.id.tv_num);
        tvSerach=findViewById(R.id.tvSerach);
        btnSearch=findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoftv=tvSerach.getText().toString().toLowerCase(Locale.ROOT);
            }
        });

        tvNumPerson.setText("Person number of gym = "+db.getNumPerson());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddPerson.class);
                startActivity(intent);
            }
        });

        adapter.notifyDataSetChanged();











    }
}
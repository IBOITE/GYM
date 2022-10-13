package com.ibo.mygym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Controller.DataBaseHandler;
import Model.Person;

public class AddPerson extends AppCompatActivity {
    TextView aName,aLname,aStartDate,aEndDate,aDescription;
    Button aBtnSave,aBtnBack;
    DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        aName=findViewById(R.id.A_tvName);
        aLname=findViewById(R.id.A_tvLname);
        aStartDate=findViewById(R.id.A_tvStartDate);
        aEndDate=findViewById(R.id.A_tvEndDate);
        aDescription=findViewById(R.id.A_tvDescription);
        aBtnSave=findViewById(R.id.A_btnSave);
        aBtnBack=findViewById(R.id.A_btnBack);
        db=new DataBaseHandler(this);

        aBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        aBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addPerson(new Person(aName.getText().toString(),aLname.getText().toString(),aStartDate.getText().toString(),aEndDate.getText().toString(),aDescription.getText().toString()));
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
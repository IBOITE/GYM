package com.ibo.mygym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Controller.DataBaseHandler;
import Controller.ListAdapter;
import Model.Person;

public class PersonDetails extends AppCompatActivity {
    TextView dId,dName,dLname,dStartDate,dEndDate,dDiscription;
    Button dBtnSave,dBtnBack,dBtnDelete;
    Bundle bundle;
    DataBaseHandler db;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        bundle=getIntent().getExtras();

        dId=findViewById(R.id.d_tvId);
        dName=findViewById(R.id.d_tvName);
        dLname=findViewById(R.id.d_tvLname);
        dStartDate=findViewById(R.id.d_tvStartDate);
        dEndDate=findViewById(R.id.d_tvEndDate);
        dDiscription=findViewById(R.id.d_tvDescription);
        dBtnSave=findViewById(R.id.d_btnEdit);
        dBtnBack=findViewById(R.id.d_btn_back);
        dBtnDelete=findViewById(R.id.d_btnDelete);
        db=new DataBaseHandler(this);

        dId.setText(bundle.getInt("id")+"");
        dName.setText(bundle.getString("name"));
        dLname.setText(bundle.getString("lastName"));
        dStartDate.setText(bundle.getString("startDate"));
        dEndDate.setText(bundle.getString("endDate"));
        dDiscription.setText(bundle.getString("description"));


        dBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updatePerson(new Person(Integer.valueOf(dId.getText().toString()),dName.getText().toString(),dLname.getText().toString(),dStartDate.getText().toString()
                        ,dEndDate.getText().toString(),dDiscription.getText().toString()));
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                listAdapter.notifyDataSetChanged();
                finish();

            }
        });
        dBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                

                finish();

            }
        });
        dBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deletePerson(new Person(Integer.valueOf(dId.getText().toString()),dName.getText().toString(),dLname.getText().toString(),dStartDate.getText().toString(),dEndDate.getText().toString()
                        ,dDiscription.getText().toString()));



                listAdapter.notifyDataSetChanged();


            }
        });


    }
}
package com.example.farmerapmcportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    //Prof Indu Anoop: Always initilize the array list before adding items
    ArrayList<model> dataholder=new ArrayList<>();
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        recyclerView = (RecyclerView)findViewById(R.id.view_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = new DBHelper(this).fetch();
        while (cursor.moveToNext()){
            model obj = new model(cursor.getString(4),cursor.getString(5), cursor.getString(7));
            dataholder.add(obj);
        }

        myadapter adapter = new myadapter(dataholder);
        recyclerView.setAdapter(adapter);


        back = findViewById(R.id.dashboard_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
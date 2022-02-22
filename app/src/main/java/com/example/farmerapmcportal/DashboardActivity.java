package com.example.farmerapmcportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView c1, c2, c3, c4, c5, c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        c1 = (CardView) findViewById(R.id.profile_card);
        c2 = (CardView) findViewById(R.id.shop_card);
        c3 = (CardView) findViewById(R.id.orders_cart);
        c4 = (CardView) findViewById(R.id.orders_list);
        c5 = (CardView) findViewById(R.id.benefits_card);
        c6 = (CardView) findViewById(R.id.logout_card);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.profile_card:
                i = new Intent(this,ProfileActivity.class);
                startActivity(i);
                break;

            case R.id.shop_card:
                i = new Intent(getApplicationContext(),ShopActivity.class);
                startActivity(i);
                break;

            case R.id.orders_cart:
                i = new Intent(this,CartActivity.class);
                startActivity(i);
                break;

            case R.id.orders_list:
                i = new Intent(this,DashboardActivity.class);
                startActivity(i);
                break;

            case R.id.benefits_card:
                i = new Intent(this,DashboardActivity.class);
                startActivity(i);
                break;

            case R.id.logout_card:
                i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
        }
    }

}
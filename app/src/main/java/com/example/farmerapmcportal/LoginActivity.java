package com.example.farmerapmcportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText mob_no, pass;
    Button b_login, signup;
    DBHelper DB;
    Button b_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        mob_no = (EditText) findViewById(R.id.mob_number);
        pass = (EditText) findViewById(R.id.password);
        b_login = (Button) findViewById(R.id.btn_login);
        signup = (Button) findViewById(R.id.add_user);
        DB = new DBHelper(this);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
//                startActivity(intent);
                String mob = mob_no.getText().toString();
                String password = pass.getText().toString();

                if (mob.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkmobno = DB.checkmob_nopassword(mob, password);
                    if(checkmobno==true){
                        if (mob.equals("8433664707")){
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Admin_addProductsActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                            startActivity(intent);
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        b_admin = findViewById(R.id.btn_admin);
        b_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Admin_addProductsActivity.class);
                startActivity(intent);
            }
        });

        signup = findViewById(R.id.add_user);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mob = mob_no.getText().toString();
                String password = pass.getText().toString();

                if (mob.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.insertData(mob, password);
                    if (insert==true){
                        Toast.makeText(LoginActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
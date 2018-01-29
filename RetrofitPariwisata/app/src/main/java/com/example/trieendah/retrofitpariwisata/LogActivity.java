package com.example.trieendah.retrofitpariwisata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        final EditText username = (EditText)findViewById(R.id.username_log);
        final EditText password = (EditText)findViewById(R.id.password_log);

        Button masuk = (Button)findViewById(R.id.masuk);
        Button register = (Button) findViewById(R.id.regis);

//        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        if (sharedPref.getString("password","") == "admin"){
//            Intent keMainMenu = new Intent(getApplicationContext(), MainActivity2.class);
//            startActivity(keMainMenu);
//        }
//        else if(sharedPref.getString("password","")!= "admin"){
//            Intent keMainMenu2 = new Intent(getApplicationContext(), PariwisataUserActivity.class);
//            startActivity(keMainMenu2);
//        }


        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals("admin"))
                {
                    Intent sa = new Intent(getApplicationContext(), PariwisataUserActivity.class);
                    SharedPreferences sharedPref = getSharedPreferences("userInfo2", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username",username.getText().toString());
                    editor.apply();
                    startActivity(sa);
                }
                else {
                    Intent sa = new Intent(getApplicationContext(), MainActivity2.class);
                    SharedPreferences sharedPref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref2.edit();
                    editor.putString("username",username.getText().toString());
                    editor.apply();
                    startActivity(sa);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sa = new Intent(getApplicationContext(), AddUserActivity.class);
                startActivity(sa);
            }
        });
    }
}

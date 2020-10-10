package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Dbmanager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db= new Dbmanager(this);
        final Button log = (Button)findViewById(R.id.log);
        CheckBox remember = findViewById(R.id.cb);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent = new Intent(login.this,nav.class);
            startActivity(intent);
        }else if(checkbox.equals("fasle")){
            Toast.makeText(this,"please sign",Toast.LENGTH_SHORT).show();
        }


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                EditText editText2 = (EditText)findViewById(R.id.editText2);
                String name = editText.getText().toString();
                String s2 = editText2.getText().toString();
                new Dbmanager(login.this);
                if(TextUtils.isEmpty(editText.getText().toString()))
                {
                    editText.setError("Cannot be empty");
                    Intent start = new Intent(getApplicationContext(),MainActivity.class);
                }
                else if (TextUtils.isEmpty(editText2.getText().toString()) | editText2.length()!=10)
                {
                    editText2.setError("please enter a valid phone number");
                    Intent start = new Intent(getApplicationContext(),MainActivity.class);
                }
                else {
                    Boolean check = db.check(s2);
                    if(check==true)
                    {
                        Toast.makeText(login.this,"Sorry, you are not registered!please signUp first!!",Toast.LENGTH_SHORT).show();
                    }
                    else {

                            Intent start = new Intent(getApplicationContext(), nav.class);
                            start.putExtra("com.example.myapplication.some", name);
                            start.putExtra("com.example.myapplication.ph", s2);
                            startActivity(start);

                    }
                }

            }
        });

        Button sign= (Button)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                EditText editText2 = (EditText)findViewById(R.id.editText2);
                String s1 = editText.getText().toString();
                String s2 = editText2.getText().toString();
                Boolean check = db.check(s2);
                if(check== true) {
                    Boolean insert = db.insert(s1, s2);
                    if (insert == true) {
                        Toast.makeText(login.this, "Congratulations!!your account has been created.Please login", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(login.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(login.this, "Account already exist!!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(login.this,"checked",Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(login.this,"unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.example.androidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidstudy.utils.MD5Utils;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
//        initData();

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        etUsername = findViewById(R.id.et_username);
        etUsername.setText(username);
        etPassword = findViewById(R.id.et_password);


        Button bt_bos = (Button) findViewById(R.id.bt_bos);
        bt_bos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
        Button bt_log = (Button) findViewById(R.id.bt_log);
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,RegActivity.class);
//                startActivity(i);
                SharedPreferences pref = getSharedPreferences("userInfo",MODE_PRIVATE);
                String password = pref.getString("password","");
                String username = etUsername.getText().toString();
                String pwd = etPassword.getText().toString();
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(MainActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }else if (!MD5Utils.md5(pwd).equals(password)){
                    Toast.makeText(MainActivity.this,"密码错误!",Toast.LENGTH_LONG).show();
                }else if (MD5Utils.md5(pwd).equals(password)){
                    Toast.makeText(MainActivity.this,"登陆成功!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,TopActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

//    private void initData() {
//        String username = readPref();
//        if (!TextUtils.isEmpty(username)){
//            etUsername.setText(username);
//        }
//    }
//
//    private String readPref() {
//        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
//        return sp.getString("username","");
//    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.title_toolbar);
        toolbar.setTitle("登陆");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
    }
}

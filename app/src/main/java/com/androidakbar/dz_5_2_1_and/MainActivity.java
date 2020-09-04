package com.androidakbar.dz_5_2_1_and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "accountDB.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appToolbar = findViewById(R.id.app_toolbar);
        appToolbar.setTitle(R.string.name_dz);
        appToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryText));
        appToolbar.setTitleTextAppearance(this, R.style.ToolBarTitleTextAppearance);

        Button btnRegistration = (Button) findViewById(R.id.btn_registration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveAccount();
            }
        });

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReadAccount();
            }
        });


    }

    private void ReadAccount() {
        EditText edLogin = (EditText) findViewById(R.id.ed_login);
        EditText edPassword = (EditText) findViewById(R.id.ed_password);
        if (edLogin.getText().length() >= getResources().getInteger(R.integer.min_login) &&
                edPassword.getText().length() >= getResources().getInteger(R.integer.min_password)) {
            //чтение из  файл
            Account account = new Account(edLogin.getText().toString(), edPassword.getText().toString());
            AccountFileManager accountFileManager = new AccountFileManager(FILE_NAME);
            if (accountFileManager.ReadFromFile(this, account)) {
                Toast.makeText(this, "Акаунт авторизован", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Нет такого аккаунта или неверные логин/пароль", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Несоответствие требованиям к длине логина или пароля", Toast.LENGTH_LONG).show();
        }
    }

    private void SaveAccount() {
        EditText edLogin = (EditText) findViewById(R.id.ed_login);
        EditText edPassword = (EditText) findViewById(R.id.ed_password);
        if (edLogin.getText().length() >= getResources().getInteger(R.integer.min_login) &&
                edPassword.getText().length() >= getResources().getInteger(R.integer.min_password)) {
            //записать в файл
            Account account = new Account(edLogin.getText().toString(), edPassword.getText().toString());
            AccountFileManager accountFileManager = new AccountFileManager(FILE_NAME);
            accountFileManager.SaveToFile(this, account);
        } else {
            Toast.makeText(this, "Несоответствие требованиям к длине логина или пароля", Toast.LENGTH_LONG).show();
        }
    }



}
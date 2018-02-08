package com.example.carlin.munisolidos;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.HttpClientStack;
import com.example.carlin.munisolidos.view.CreateCiudadanoActivity;
import com.example.carlin.munisolidos.view.ReporteSolidosActivity;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    //ruta de la vista reporte
/*
    public void goreporteCiudadano(View view)
    {
        if (txtUsuario.getText().toString().equals("carlin") && txtCOntraseña.getText().toString().equals("123")) {
            Intent intn = new Intent(this, ReporteSolidosActivity.class);
            startActivity(intn);
        }else
        {
            Toast.makeText(getApplicationContext(),"usuario o Contraseña incorrecta",Toast.LENGTH_LONG).show();
        }
    }
*/
    //funcion que muestra la vista de registrar Ciudadano---
    public void goCreateCiudadano(View view)
    {
        Intent intent = new Intent(this, CreateCiudadanoActivity.class);//de donde this a donde quiero ir CreateCiudadanoActivity.class
        startActivity(intent);
    }


}

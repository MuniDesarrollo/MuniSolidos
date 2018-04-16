package com.example.carlin.munisolidos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.HttpClientStack;
import com.example.carlin.munisolidos.view.CreateCiudadanoActivity;
import com.example.carlin.munisolidos.view.ReporteSolidosActivity;
import com.example.carlin.munisolidos.view.conteinerActivity;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity  {

    EditText usu, pas;
    Button login;
    //parametros de conexion al web socket....
    final static String PARAM_NAME = "name";
    //final static String PARAM_IMAGE = "image";
    Socket socket;
    JSONObject obj = new JSONObject();
    final static String PARAM_USUARIO = "usuario";
    final static  String PARAM_CONTRASENIA="contrasenia";
    TextView mensaje1;
    TextView mensaje2;
    String validar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        usu=(EditText)findViewById(R.id.txtusuario);
        pas=(EditText)findViewById(R.id.txtcontrasenia);

        login=(Button)findViewById(R.id.btnLogin);
       // socket.on("respuesta",datos);
        //socket.connect();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    obj.put(PARAM_USUARIO,usu.getText().toString().trim());
                    obj.put(PARAM_CONTRASENIA,pas.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("validar", obj);
               Toast.makeText(getBaseContext(),"Datos enviados con Exito",Toast.LENGTH_LONG).show();
                //Conectar();

            }
        });


        //conexion a socket io

        try{
            /* Instance object socket */
            socket = IO.socket("http://192.168.15.202:7000");

            // obj.put(PARAM_NAME, "Pablo");
            socket.connect();
            Toast.makeText(this,"se conecto correctamente",Toast.LENGTH_SHORT).show();
            // socket.emit("my event", obj);

        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }




    //ruta de la vista reporte

    public void goreporteCiudadano(View view)
    {

        Intent intn = new Intent(this, conteinerActivity.class);
        startActivity(intn);

    }

    //funcion que muestra la vista de registrar Ciudadano---
    public void goCreateCiudadano(View view)
    {
        Intent intent = new Intent(this, CreateCiudadanoActivity.class);//de donde this a donde quiero ir CreateCiudadanoActivity.class
        startActivity(intent);
    }


}
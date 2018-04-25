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
import org.w3c.dom.Text;

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
        //conexion a socket io
        try{
            /* Instance object socket */
            socket = IO.socket("http://192.168.15.202:7000");
            //socket.connect();
            Toast.makeText(this,"se conecto correctamente",Toast.LENGTH_SHORT).show();;
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    obj.put(PARAM_USUARIO,usu.getText().toString().trim());
                    obj.put(PARAM_CONTRASENIA,pas.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("validar", obj);///enviamos los datos obtenidos desde los edidtext usuario y contraseña
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {//obtenemos la respuesta del servidor node js

                    @Override
                    public void call(Object... args) {
                        Log.d("ActivityName: ", "socket connected");

                        // emite  aqui todo lo quieras al servidor node js
                        //socket.emit("login", some);
                        //socket.disconnect();
                    }

                    // this is the emit from the server
                }).on("respuesta", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        // this argas[0] obtenemos todo lo que manda desde el servidor node js
                        int id=0;
                        final JSONArray json = (JSONArray) args[0];
                        final String message = json.toString();


                            JSONObject object = null;
                            try {
                                object = json.getJSONObject(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String user = null;
                            String pass = null;

                            try {
                                user = object.getString("usuario");
                                pass = object.getString("contrasenia");
                                id = object.getInt("idTciudadano");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            final String finaluser = user;
                            final String finalpass = pass;

                            final int finalid = id;

                        final JSONObject finalObject = object;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                // do something
                                //mListData is the array adapter
                                //enviamos a una nueva ventana
                              Toast.makeText(getBaseContext(),"dato"+ finalObject,Toast.LENGTH_LONG).show();
                                /*
                                if (finalObject.length()>0) {

                                    Intent intn = new Intent(LoginActivity.this, conteinerActivity.class);
                                    intn.putExtra("idCiudadano",finalid);//mandamos el id al ReportarFragment
                                    startActivity(intn);
                                    Toast.makeText(getBaseContext(), "usuario" + message+finalid, Toast.LENGTH_LONG).show();
                                }
                                else {

                                    Toast.makeText(getBaseContext(), "Usuario o Contraseña incorrecta", Toast.LENGTH_LONG).show();
                                    }*/
                            }
                        });
                    }
                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("ActivityName: ", "socket disconnected");
                    }
                });
                socket.connect();//hacemos la conexion al servidor node js-
            }
        });
    }

    //funcion que manda el id del ciudadano


    //funcion que muestra la vista de registrar Ciudadano---
    public void goCreateCiudadano(View view)
    {
        Intent intent = new Intent(this, CreateCiudadanoActivity.class);//de donde this a donde quiero ir CreateCiudadanoActivity.class
        startActivity(intent);
    }

}
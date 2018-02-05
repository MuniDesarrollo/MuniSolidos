package com.example.carlin.munisolidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carlin.munisolidos.view.CreateCiudadanoActivity;
import com.example.carlin.munisolidos.view.ReporteSolidosActivity;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsuario,txtCOntraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtCOntraseña=(EditText)findViewById(R.id.txtContraseña);

        btnLogin=(Button)findViewById(R.id.btnLogin);

    }
    //ruta de la vista reporte

    public void goreporteCiudadano(View view)
    {
        Intent intn=new Intent(this, ReporteSolidosActivity.class);
        startActivity(intn);
    }

    //funcion que muestra la vista de registrar Ciudadano---
    public void goCreateCiudadano(View view)
    {
        Intent intent = new Intent(this, CreateCiudadanoActivity.class);//de donde this a donde quiero ir CreateCiudadanoActivity.class
        startActivity(intent);
    }

    //funcion que insertara campos a la base de datos mediante URL

    public  String enviarDatosGET(String usu, String pas)
    {
        URL url=null;
        String linea;
        int respuesta;
        StringBuilder resul=null;

        try
        {
            url =new URL("http://192.168.15.18:80/AppSolidos/validacionAcceso.php?usuario="+usu+"&contrasenia="+pas);
            HttpURLConnection conexion=(HttpURLConnection)url.openConnection();//abre la conexion de la url
            respuesta=conexion.getResponseCode();//muestra la respuesta de la conexion

            if (respuesta==HttpURLConnection.HTTP_OK)//verificamos la conexion a la data base mediante la url
            {
                InputStream in =new BufferedInputStream(conexion.getInputStream());//obtenemos la conexion
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));//leemos los datos que se obtuvieron de la url

                while ((linea=reader.readLine())!=null)
                {
                    resul.append(linea);
                }
            }

        }
         catch (IOException e) {
            e.printStackTrace();
        }
        return resul.toString();
    }

    public int obtDatosJSON(String response)
    {
        int res=0;
        try
        {
            JSONArray json=new JSONArray(response);
            if (json.length()>0)//verificamos los elementos de json
            {
                res=1;
            }
        }catch (Exception e)
        {

        }
        return res;
    }
}

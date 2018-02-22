package com.example.carlin.munisolidos.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlin.munisolidos.Encapsulamiento.CLciudadano;
import com.example.carlin.munisolidos.LoginActivity;
import com.example.carlin.munisolidos.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class CreateCiudadanoActivity extends AppCompatActivity {


    EditText Dni,Nombre,Apllidos,Correo,Usuario,Contrasenia;
    Button btnCrearCiu;
    HttpClient client;
    HttpPost post;
    List<NameValuePair> listnvp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ciudadano);
        showToolbar(getResources().getString(R.string.toolbar_titulo_createciudadano),true);//el true activa la flecha de atras

        Dni=(EditText)findViewById(R.id.txtDni);
        Nombre=(EditText)findViewById(R.id.txtNombre);
        Apllidos=(EditText)findViewById(R.id.txtApellido);
        Correo=(EditText)findViewById(R.id.txtcorreo);
        Usuario=(EditText)findViewById(R.id.txtUsuario);
        Contrasenia=(EditText)findViewById(R.id.txtContraseña);

        btnCrearCiu=(Button)findViewById(R.id.btnCrearCiudadano);
        btnCrearCiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dni.getText().toString().trim().equals("") )
                {
                    Toast.makeText(CreateCiudadanoActivity.this,"Llene los campos",Toast.LENGTH_LONG).show();
                    Dni.requestFocus();

                }
                else
                {
                    new CreateCiudadanoActivity.EnviarDatos(CreateCiudadanoActivity.this).execute();
                }
            }
        });
    }

///metodo que recibe los datos  a la base de datos ....mediante url(Web Servis)
    private boolean enviarDatos()
    {
        client =new DefaultHttpClient();
        post=new HttpPost("http://192.168.15.18/AppSolidos/insertarDatos.php");
        //CLciudadano cLciudadano=new CLciudadano();
        listnvp=new ArrayList<NameValuePair>(6);

        listnvp.add(new BasicNameValuePair("cdni",Dni.getText().toString().trim()));
        listnvp.add(new BasicNameValuePair("cnombre",Nombre.getText().toString().trim()));
        listnvp.add(new BasicNameValuePair("capellidos",Apllidos.getText().toString().trim()));
        listnvp.add(new BasicNameValuePair("ccorreo",Correo.getText().toString().trim()));
        listnvp.add(new BasicNameValuePair("cusuario",Usuario.getText().toString().trim()));
        listnvp.add(new BasicNameValuePair("ccontrasenia",Contrasenia.getText().toString().trim()));

        try
        {
            post.setEntity(new UrlEncodedFormEntity(listnvp));
            client.execute(post);
            return true;
        }
        catch (Exception e)
        {}
        return false;
    }

    class EnviarDatos extends AsyncTask<String,String,String>
    {
        private Activity context;
        EnviarDatos(Activity context)
        {
            this.context=context;
        }

        @Override
        protected String doInBackground(String... strings) {

            if (enviarDatos())
            {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"Datos enviados con Exito",Toast.LENGTH_LONG).show();
                        Limpiar();
                    }
                });
            }
            else
            {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"Datos no enviados",Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }
    }

    //metodo que limpia los cuadros de texto...................
    public  void  Limpiar()
    {
        Dni.setText("");
        Nombre.setText("");
        Apllidos.setText("");
        Correo.setText("");
        Usuario.setText("");
        Contrasenia.setText("");
    }

    public void showToolbar(String titulo, boolean upButton)//muestra el titulo y flecha de regreso............
    {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);//asigna el titulo al toobar
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton); //asigna la flecha de regreso al toolbar
    }
}

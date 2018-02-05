package com.example.carlin.munisolidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carlin.munisolidos.view.CreateCiudadanoActivity;

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
    public void goCreateCiudadano(View view)
    {
        Intent intent = new Intent(this, CreateCiudadanoActivity.class);//de donde this a donde quiero ir CreateCiudadanoActivity.class
        startActivity(intent);
    }
}

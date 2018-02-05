package com.example.carlin.munisolidos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.carlin.munisolidos.R;

public class ReporteSolidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_solidos);
        showToolbar(getResources().getString(R.string.toolbar_titulo_reporte),true);//el true activa la flecha de atras
    }

    public void showToolbar(String titulo, boolean upButton)
    {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);//asigna el titulo al toobar
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton); //asigna la flecha de regreso al toolbar
    }

}

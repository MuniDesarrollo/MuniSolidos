package com.example.carlin.munisolidos.view;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carlin.munisolidos.R;

public class ReporteSolidosActivity extends AppCompatActivity {

    //atributos de la tabl tipo residuo....
    EditText tipo;
    TextView fecha;
//Botones......
    Button btnubicacion,btnfoto,btnreportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_solidos);
        showToolbar(getResources().getString(R.string.toolbar_titulo_reporte),true);//el true activa la flecha de atras
        tipo=(EditText)findViewById(R.id.txtTipoResiduo);
        fecha=(TextView)findViewById(R.id.txtFecha);
        btnfoto=(Button)findViewById(R.id.btnTomarFoto);
        btnubicacion=(Button)findViewById(R.id.btnMiUbicacion);
        btnreportar=(Button)findViewById(R.id.btnReportar);

       // fecha=getMenuInflater();
    }

    public void showToolbar(String titulo, boolean upButton)
    {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);//asigna el titulo al toobar
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton); //asigna la flecha de regreso al toolbar
    }

    //Calendar calendar=new Calendar.getInstace();
}

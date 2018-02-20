package com.example.carlin.munisolidos.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.carlin.munisolidos.R;

import java.io.File;

public class ReporteSolidosActivity extends AppCompatActivity {

    private  static  final String CARPETA_PRINCIPAL="misImagenesApp/";//directorio principal
    private  static  final String CARPETA_IMAGEN="miImagen/";//carpeta donde se almacenara las fotos
    private  static  final String DIRECTORIO_IMAGEN=CARPETA_PRINCIPAL + CARPETA_IMAGEN;//
    private  String path;
    File filaImagen;
    Bitmap bitmap;

    private static final int COD_SELECCIONA=10;
    private static  final int COD_FOTO=20;
    //atributos de la tabl tipo residuo....
    EditText tipo;
    TextView fecha;
    Button btnubicacion,btnfoto,btnreportar;

    ImageView imgFoto;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

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

    //metodo para tomar foto por opciones-----------

    private  void mostrarDialogoOpciones()
    {
     final CharSequence[] opciones={"Tomar Foto"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Elija una opcion");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(opciones[i].equals("Tomar Foto"))
                {
                    abrirCamara();
                    //llamda para activar la camara
                    //Toast.makeText(getBaseContext(),"cargar camara",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirCamara() {

        File miFile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada=miFile.exists();
        if (isCreada==false)
        {
            isCreada=miFile.mkdirs();
        }
        if (isCreada==true)
        {
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre=consecutivo.toString()+".jpg";
            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN+File.separator+nombre;//indicamos la ruta del almacenamiento
            filaImagen=new File(path);

            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filaImagen));
            startActivityForResult(intent,COD_FOTO);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case COD_SELECCIONA:
                Uri miPath=data.getData();
                imgFoto.setImageURI(miPath);
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(getApplicationContext(),new String[]{path},null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        //Log.i(path);
                    }
                });
        }
    }

}

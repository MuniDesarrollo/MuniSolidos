package com.example.carlin.munisolidos.view.fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.carlin.munisolidos.R;
import com.example.carlin.munisolidos.view.ReporteSolidosActivity;
import com.example.carlin.munisolidos.view.conteinerActivity;

import org.json.JSONObject;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportarFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    //parametros de ......

    private  String mParamt2;


    private  static  final String CARPETA_PRINCIPAL="misImagenesApp/";//directorio principal
    private  static  final String CARPETA_IMAGEN="miImagen/";//carpeta donde se almacenara las fotos
    private  static  final String DIRECTORIO_IMAGEN=CARPETA_PRINCIPAL + CARPETA_IMAGEN;//
    private  String path;
    File filaImagen;
    Bitmap bitmap;

    private static final int COD_SELECCIONA=10;
    private static  final int COD_FOTO=20;
    //atributos de la tabl tipo residuo....
    EditText Descripcion;
    TextView FechaReportado;
    Button btnubicacion,btnfoto,btnreportar;

    ImageView imgFoto;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    ProgressDialog progressDialog;
    public ReportarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_reportar, container, false);

        Descripcion=(EditText)vista.findViewById(R.id.txtDescripcionTipo);
        FechaReportado=(TextView)vista.findViewById(R.id.txtFecha);
        btnfoto=(Button)vista.findViewById(R.id.btnTomarFoto);
        btnubicacion=(Button)vista.findViewById(R.id.btnMiUbicacion);
        btnreportar=(Button)vista.findViewById(R.id.btnReportar);


        request= Volley.newRequestQueue(getContext());
        btnreportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebservice();
            }
        });
        return vista;

    }

    private void cargarWebservice() {

        String url=" ";
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }

    private  void mostrarDialogoOpciones()
    {
        final CharSequence[] opciones={"Tomar Foto"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elija una opcion");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(opciones[i].equals("Tomar Foto"))
                {
                    abrirCamara();
                    //llamda para activar la camara
                    Toast.makeText(getContext(),"cargar camara",Toast.LENGTH_SHORT).show();
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

}

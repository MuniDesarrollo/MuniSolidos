package com.example.carlin.munisolidos.view.fragment;


import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportarFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    //parametros de ......

   // private  String mParamt2;


    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios

    private  String path;
    File filaImagen;
    Bitmap bitmap;

    private static final int COD_SELECCIONA=10;
    private static  final int COD_FOTO=20;
    //atributos de la tabl tipo residuo....
    EditText Descripcion;
    TextView FechaReportado, Fecharecogido;
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
        FechaReportado.setText("Fecha de Reporte :"+fechaDelSistema());

        Fecharecogido=null;
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
        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(),"cargar camara",Toast.LENGTH_SHORT).show();
                mostrarDialogoOpciones();
            }
        });
        return vista;
    }

    private void cargarWebservice() {

       // Toast.makeText(getContext(),"la fecha es:"+fecha,Toast.LENGTH_LONG).show();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("En progreso.....");
        progressDialog.show();
        String url="http://192.168.15.18/AppSolidos/insertarDetalleReporte.php?fechareportado="+fechaDelSistemaDB()+"&fecharecogido="+Fecharecogido+"&imagen="+imgFoto+"&longitud="+1.02302032+"&latitud="+2.2332+"&descripcion="+Descripcion.getText().toString()+"&idTciudadano="+1;

       // url=url.replace("","%20");//es para remplazar los espacios en blnco.....
        jsonObjectRequest =new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getContext(),"Se reporto correctamente",Toast.LENGTH_LONG).show();
        progressDialog.hide();//oculta el mensaje en progreso
        Descripcion.setText(" ");
    }
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(),"No se ha podido registrar correctamente.!"+error.toString(),Toast.LENGTH_LONG).show();
        progressDialog.hide();//oculta el mensaje en progreso
        Log.i("ERROR",error.toString());
    }

    //obteniendo la fecha del sistema que sera la fecha de reporte... del residuo----
    public String fechaDelSistemaDB()
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        Date date=new Date();
        String fecha=dateFormat.format(date);
        return fecha.toString();
    }
    public String fechaDelSistema()//para mostrar en TextVie del fragnment......
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date=new Date();
        String fecha=dateFormat.format(date);
        return fecha.toString();
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

                }
            }
        });
        builder.show();//muestra el mensaje de dialogo.....-.-..-.-..-.-.-.

    }

    private void abrirCamara()
    {

        File miFile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada=miFile.exists();

        if (isCreada==false)
        {
            isCreada=miFile.mkdirs();
           // Toast.makeText(getContext(),"Se ha creado el directorio--",Toast.LENGTH_SHORT).show();
        }

        if (isCreada==true)
        {
           // Toast.makeText(getContext(),"Se ha creado el directorio--",Toast.LENGTH_SHORT).show();
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre=consecutivo.toString()+".jpg";
            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN+File.separator+nombre;//indicamos la ruta del almacenamiento
            filaImagen=new File(path);

            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filaImagen));
            startActivityForResult(intent,COD_FOTO);
            //startActivityForResult(intent,COD_FOTO);
        }



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case COD_SELECCIONA:
                Uri miPaht=data.getData();
                imgFoto.setImageURI(miPaht);
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(getContext(),new String[]{path},null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("path",""+path);
                    }
                });
                bitmap = BitmapFactory.decodeFile(path);
                imgFoto.setImageBitmap(bitmap);
                break;
        }
       // bitmap=redimensionarImagen(bitmap,600,800);
    }

    public  void Limpiar()//limpia las casillas de losTextviex de formulario de FragmentReporte..
    {
        FechaReportado.setText("");
        Fecharecogido.setText("");
        Descripcion.setText("");
    }

}

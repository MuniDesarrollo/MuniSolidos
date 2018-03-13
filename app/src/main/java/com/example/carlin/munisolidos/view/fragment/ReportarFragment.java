package com.example.carlin.munisolidos.view.fragment;


import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
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
import com.example.carlin.munisolidos.LoginActivity;
import com.example.carlin.munisolidos.R;
import com.example.carlin.munisolidos.view.ReporteSolidosActivity;
import com.example.carlin.munisolidos.view.conteinerActivity;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportarFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {


   // private  String mParamt2;

    private static  final  int CAMERA_REQUEST_CODE=1;
    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios


    private  String path;//para almacenar la ruta de la imagen
    File filaImagen,fileImagen;
    Bitmap bitmap;

    Socket socket;
   // final static String PARAM_NAME = "name";
    final static String PARAM_FECHAREPORTE = "freporte";
    final static String PARAM_ESTADO="estado";
    final static String PARAM_FECHARECOGIDO = "frecogido";
    final static String PARAM_FOTO = "foto";
    final static String RUTA_IMAGEN="rutaImagen";
    final static String PARAM_LONGITUD = "longitud";
    final static String PARAM_LATITUD = "latitud";
    final static String PARAM_DESCRIPCION = "descripcion";
    final static String ID_CAMION="idCamion";
    final static String ID_CIUDADANO="idCiudadano";


    private static final int COD_SELECCIONA=10;
    private static  final int COD_FOTO=20;

    //atributos de la tabl tipo residuo....
    EditText Descripcion;
    TextView FechaReportado, Fecharecogido;
    Button btnubicacion,btnfoto,btnreportar;

    ImageView imgFoto,imgReportes;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    JSONObject obj = new JSONObject();
    ProgressDialog progressDialog;
    TextView mensaje1;
    TextView mensaje2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_reportar, container, false);

        Descripcion=(EditText)vista.findViewById(R.id.txtDescripcionTipo);
        FechaReportado=(TextView)vista.findViewById(R.id.txtFecha);
        FechaReportado.setText("Fecha de Reporte: "+fechaDelSistema());
        imgReportes=(ImageView)vista.findViewById(R.id.imgReporte);

        Fecharecogido=null;
        btnfoto=(Button)vista.findViewById(R.id.btnTomarFoto);
        btnubicacion=(Button)vista.findViewById(R.id.btnMiUbicacion);
        btnreportar=(Button)vista.findViewById(R.id.btnReportar);

        mensaje1 = (TextView) vista.findViewById(R.id.mensaje_id);
        mensaje2 = (TextView) vista.findViewById(R.id.mensaje_id2);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
        try{
            /* Instance object socket */
            socket = IO.socket("http://192.168.15.18:8081");


            // obj.put(PARAM_NAME, "Pablo");
            socket.connect();
            Toast.makeText(getContext(),"se conecto correctamente",Toast.LENGTH_SHORT).show();
            // socket.emit("my event", obj);

        }catch (URISyntaxException e) {
            e.printStackTrace();

        }


        //Permisos
        if(solicitaPermisosVersionesSuperiores()){
            btnfoto.setEnabled(true);
        }else{
            btnfoto.setEnabled(false);
        }

        request= Volley.newRequestQueue(getContext());

        btnreportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // cargarWebservice();

                try {
                    obj.put(PARAM_FECHAREPORTE,fechaDelSistemaDB());
                    obj.put(PARAM_ESTADO,1);
                    obj.put(PARAM_FECHARECOGIDO,null);
                    obj.put(PARAM_FOTO,imgFoto);
                    obj.put(RUTA_IMAGEN,null);
                    obj.put(PARAM_LONGITUD,1.325646);
                    obj.put(PARAM_LATITUD,0.124545454);
                    obj.put(PARAM_DESCRIPCION,Descripcion.getText());
                    obj.put(ID_CAMION,null);
                    obj.put(ID_CIUDADANO,1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("my event", obj);

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


    private void locationStart() {

        LocationManager mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity((LoginActivity)getContext());
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        mensaje1.setText("Localización agregada");
        mensaje2.setText("");
    }



    public void setLocation(android.location.Location loc) {
//Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        LoginActivity mainActivity;
        public LoginActivity getMainActivity() {
            return mainActivity;
        }
        public void setMainActivity(LoginActivity mainActivity) {
            this.mainActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(android.location.Location loc) {
// Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
// debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            mensaje1.setText(Text);
            //this.mainActivity.setLocation(loc);
        }
        @Override
        public void onProviderDisabled(String provider) {
// Este metodo se ejecuta cuando el GPS es desactivado
            mensaje1.setText("GPS Desactivado");
        }
        @Override
        public void onProviderEnabled(String provider) {
// Este metodo se ejecuta cuando el GPS es activado
            mensaje1.setText("GPS Activado");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


    private boolean solicitaPermisosVersionesSuperiores() {

        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){//validamos si estamos en android menor a 6 para no buscar los permisos
            return true;
        }

        //validamos si los permisos ya fueron aceptados
        if((getContext().checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&& getContext().checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED){
            return true;
        }


        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)||(shouldShowRequestPermissionRationale(CAMERA)))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);
        }

        return false;//implementamos el que procesa el evento dependiendo de lo que se defina aqui
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){//el dos representa los 2 permisos
                Toast.makeText(getContext(),"Permisos aceptados",Toast.LENGTH_SHORT);
                btnfoto.setEnabled(true);

            }
        }else{
            solicitarPermisosManual();
            locationStart();
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(getContext());//estamos en fragment
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getContext().getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
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
        boolean isCreada=miFile.exists();//verificamos la exixtencia de la CArpeta DIRECTORIO_IMAGEN

        if(isCreada==false){
            isCreada=miFile.mkdirs();
        }

        if(isCreada==true){
            Long consecutivo= System.currentTimeMillis()/100;
            String nombre=consecutivo.toString()+".jpg";

            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;//indicamos la ruta de almacenamiento

            fileImagen=new File(path);

            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));

            ////
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities=getContext().getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(getContext(),authorities,fileImagen);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else
            {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
            }
            startActivityForResult(intent,COD_FOTO);

            ////
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK)
        {
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
/*
            Uri path=data.getData();
            imgFoto.setImageURI(path);*/
        }

    }

    public  void Limpiar()//limpia las casillas de losTextviex de formulario de FragmentReporte..
    {
        FechaReportado.setText("");
        Fecharecogido.setText("");
        Descripcion.setText("");
    }


}

package com.example.carlin.munisolidos.view.fragment;


import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.carlin.munisolidos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CosultarReporteFragment extends Fragment {


    public CosultarReporteFragment() {
        // Required empty public constructor
    }

    EditText ConsultarEstado;
    TextView Descripcion,FechaReportado,Estado;
    Button btnConsultarreporte;
    ProgressDialog progressDialog;
    ImageView campoImagen;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_cosultar_reporte, container, false);
        // Inflate the layout for this fragment
        ConsultarEstado=(EditText)vista.findViewById(R.id.campoEstado);
        Descripcion=(TextView)vista.findViewById(R.id.txtDescripcion);
        FechaReportado=(TextView)vista.findViewById(R.id.txtFechaReportado);
        Estado=(TextView)vista.findViewById(R.id.txtEstado);

        campoImagen=(ImageView)vista.findViewById(R.id.imagenId);

        request= Volley.newRequestQueue(getContext());
       // btnConsultarreporte.setOnClickListener((view)->{cargarWebService();});
        return vista;
    }

    private  void cargarWebService()
    {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Consultando...");
        progressDialog.show();

        String url="http://192.168.15.18/AppSolidos/consultarCiudadanoReporte.php?estado="+ConsultarEstado.getText().toString();
        //jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        //request.add(jsonObjectRequest);

    }

}

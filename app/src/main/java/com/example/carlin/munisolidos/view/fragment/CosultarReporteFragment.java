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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.carlin.munisolidos.Encapsulamiento.ReporteReciduo;
import com.example.carlin.munisolidos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.carlin.munisolidos.R.id.txtDescripcion;

/**
 * A simple {@link Fragment} subclass.
 */
public class CosultarReporteFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {


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
        Descripcion=(TextView)vista.findViewById(txtDescripcion);
        FechaReportado=(TextView)vista.findViewById(R.id.txtFechaReportado);
        Estado=(TextView)vista.findViewById(R.id.txtEstado);

        campoImagen=(ImageView)vista.findViewById(R.id.imagenId);

        request= Volley.newRequestQueue(getContext());
       btnConsultarreporte.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cargarWebservice();
           }
       });
        return vista;
    }

    private void cargarWebservice() {

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Consultando...");
        progressDialog.show();

        // String url="http://192.168.15.18/AppSolidos/consultarCiudadanoReporte.php?estado="+ConsultarEstado.getText().toString();
        String url="http://192.168.15.18/AppSolidos/consultarImagen.php?idTdetallereporte="+ConsultarEstado.getText().toString();
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }


    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();

        Toast.makeText(getContext(),"Mensaje :"+response,Toast.LENGTH_SHORT).show();
        ReporteReciduo reporteReciduo=new ReporteReciduo();
        JSONArray json=response.optJSONArray("tdetallereporte");
        JSONObject jsonObject=null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jsonObject=json.getJSONObject(0);
            reporteReciduo.setDescripcion(jsonObject.optString("descripcion"));
           // reporteReciduo.setFechaReportado(jsonObject.optString("fechareportado"));
            reporteReciduo.setEstado(jsonObject.optInt("estado"));
            reporteReciduo.setDatos(jsonObject.optString("imagen"));
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        //obteniendo los datos para mostrar---
        Descripcion.setText("descripcion"+reporteReciduo.getDescripcion());
        Estado.setText("estado"+reporteReciduo.getEstado());
        if (reporteReciduo.getImagen()!=null) {
            campoImagen.setImageBitmap(reporteReciduo.getImagen());
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }


}

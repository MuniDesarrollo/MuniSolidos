package com.example.carlin.munisolidos.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlin.munisolidos.Encapsulamiento.ReporteReciduo;
import com.example.carlin.munisolidos.R;

import java.util.ArrayList;

/**
 * Created by CARLIN on 17/02/2018.
 */



public class ReportesImagenAdapter extends RecyclerView.Adapter<ReportesImagenAdapter.ImagenViewHolder>{

    private ArrayList<ReporteReciduo> reporteReciduos;
    private int resource;
    private Activity activity;

    public ArrayList<ReporteReciduo> getReporteReciduos() {
        return reporteReciduos;
    }

    public void setReporteReciduos(ArrayList<ReporteReciduo> reporteReciduos) {
        this.reporteReciduos = reporteReciduos;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ReportesImagenAdapter(ArrayList<ReporteReciduo> reporteReciduos, int resource, Activity activity) {
        this.reporteReciduos = reporteReciduos;
        this.resource = resource;
        this.activity = activity;
    }
    @Override
    public ImagenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);

        return new ImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagenViewHolder holder, int position) {
        ReporteReciduo reporteReciduo=reporteReciduos.get(position);//obteniendo los objetos de arraylist
        holder.descripcionCard.setText(reporteReciduo.getDescripcion());
        holder.estadoCard.setText(reporteReciduo.getEstado());
        holder.fechaReporteCard.setText((CharSequence) reporteReciduo.getFechaReportado());

    }

    @Override
    public int getItemCount() {
        return reporteReciduos.size();
    }

    public  class  ImagenViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imagenViewCard;
        private TextView descripcionCard;
        private TextView estadoCard;
        private TextView fechaReporteCard;

        public ImagenViewHolder(View itemView) {
            super(itemView);

            imagenViewCard      = (ImageView)itemView.findViewById(R.id.imagenViewCard);
            descripcionCard     = (TextView) itemView.findViewById(R.id.descripcionCard);
            estadoCard          = (TextView) itemView.findViewById(R.id.estadoCard);
            fechaReporteCard    = (TextView) itemView.findViewById(R.id.fechaReporteCardd);
        }
    }

}

package com.example.carlin.munisolidos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlin.munisolidos.R;

/**
 * Created by CARLIN on 17/02/2018.
 */

public class ImagenAdapterRecyclerView extends RecyclerView.Adapter<ImagenAdapterRecyclerView.ImagenViewHolder>{

    @Override
    public ImagenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ImagenViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

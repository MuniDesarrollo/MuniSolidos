package com.example.carlin.munisolidos.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlin.munisolidos.Encapsulamiento.ReporteReciduo;
import com.example.carlin.munisolidos.R;
import com.example.carlin.munisolidos.adapter.ImagenAdapterRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {


    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_inicio, container, false);
        showToolbar("Inicio",false,view);
        RecyclerView imagenREcycler=(RecyclerView)view.findViewById(R.id.ImagenRecycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        imagenREcycler.setLayoutManager(linearLayoutManager);
        //ImagenAdapterRecyclerView imagenAdapterRecyclerView=new ImagenAdapterRecyclerView(buidImagenes(),R.layout.cardview_imagenes,getActivity());
        //imagenREcycler.setAdapter(imagenAdapterRecyclerView);

        return view;

    }

    public ArrayList<ReporteReciduo> buidImagenes()
    {
        ArrayList<ReporteReciduo> imagenes=new ArrayList<>();
       /* imagenes.add(new ReporteReciduo("2018-02-15",1,"https://www.google.com.pe/search?biw=1536&bih=759&tbm=isch&sa=1&ei=AhuKWqiVJY32zgL40ojgDw&q=imagen+de+residuos+solidos&oq=imagen+de+residuos&gs_l=psy-ab.3.0.0l5j0i30k1l3j0i24k1.11585.16653.0.17715.11.11.0.0.0.0.143.1379.0j10.10.0....0...1c.1.64.psy-ab..1.10.1378...0i67k1j0i8i30k1.0.DVqZtW7CCKI#imgrc=tSjuU8XAx95YZM:","desecho"));
        imagenes.add(new ReporteReciduo("2018-02-16",1,"https://www.google.com.pe/search?biw=1536&bih=759&tbm=isch&sa=1&ei=AhuKWqiVJY32zgL40ojgDw&q=imagen+de+residuos+solidos&oq=imagen+de+residuos&gs_l=psy-ab.3.0.0l5j0i30k1l3j0i24k1.11585.16653.0.17715.11.11.0.0.0.0.143.1379.0j10.10.0....0...1c.1.64.psy-ab..1.10.1378...0i67k1j0i8i30k1.0.DVqZtW7CCKI#imgrc=tSjuU8XAx95YZM:","desmote"));
        imagenes.add(new ReporteReciduo("2018-02-17",1,"https://www.google.com.pe/search?biw=1536&bih=759&tbm=isch&sa=1&ei=AhuKWqiVJY32zgL40ojgDw&q=imagen+de+residuos+solidos&oq=imagen+de+residuos&gs_l=psy-ab.3.0.0l5j0i30k1l3j0i24k1.11585.16653.0.17715.11.11.0.0.0.0.143.1379.0j10.10.0....0...1c.1.64.psy-ab..1.10.1378...0i67k1j0i8i30k1.0.DVqZtW7CCKI#imgrc=tSjuU8XAx95YZM:","Papel"));
    */
        return  imagenes;
    }

    public void showToolbar(String titulo, boolean upButton ,View view)
    {

        Toolbar toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(titulo);//asigna el titulo al toobar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton); //asigna la flecha de regreso al toolbar

    }

}

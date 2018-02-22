package com.example.carlin.munisolidos.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlin.munisolidos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultarListaRoportesFragment extends Fragment {


    public ConsultarListaRoportesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultar_lista_roportes, container, false);
    }

}

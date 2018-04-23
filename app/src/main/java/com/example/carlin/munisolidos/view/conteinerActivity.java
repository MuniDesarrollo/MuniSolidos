package com.example.carlin.munisolidos.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlin.munisolidos.R;
import com.example.carlin.munisolidos.view.fragment.CosultarReporteFragment;
import com.example.carlin.munisolidos.view.fragment.InicioFragment;
import com.example.carlin.munisolidos.view.fragment.ReportarFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class conteinerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteiner);

        BottomBar bottomBar=(BottomBar)findViewById(R.id.bottombar);

        //por default
        bottomBar.setDefaultTab(R.id.inicio);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId)
                {
                    case R.id.inicio:
                        InicioFragment inicioFragment=new InicioFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,inicioFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;

                    case R.id.Reportar:
                        ReportarFragment reportarFragment=new ReportarFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,reportarFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;

                }
            }
        });
    }
}

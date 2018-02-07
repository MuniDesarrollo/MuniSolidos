package com.example.carlin.munisolidos.Encapsulamiento;

/**
 * Created by INFO_LAPTOP on 7/02/2018.
 */

public class CLresiduo {

    //atributos de la tabla residuos.......
    private String tipo;

    public CLresiduo(String _tipo)
    {
        this.setTipo(_tipo);
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

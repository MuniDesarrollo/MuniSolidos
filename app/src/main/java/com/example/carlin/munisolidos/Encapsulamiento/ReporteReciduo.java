package com.example.carlin.munisolidos.Encapsulamiento;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.Date;

/**
 * Created by CARLIN on 17/02/2018.
 */

public class ReporteReciduo {

    private Date fechaReportado;
    private int estado;
    private Date fechaRecogido;
    private Bitmap imagen;
    private String rutaImagen;
    private double latitud;
    private double longitud;
    private  String descripcion;
    private  int idTciudadano;
    private  int idTcamionRecolector;
    private  String datos;



    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
        try {
            byte[] byteCode= Base64.decode(datos,Base64.DEFAULT);
            this.setImagen(BitmapFactory.decodeByteArray(byteCode,0,byteCode.length));

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Date getFechaReportado() {
        return fechaReportado;
    }

    public void setFechaReportado(Date fechaReportado) {
        this.fechaReportado = fechaReportado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaRecogido() {
        return fechaRecogido;
    }

    public void setFechaRecogido(Date fechaRecogido) {
        this.fechaRecogido = fechaRecogido;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTciudadano() {
        return idTciudadano;
    }

    public void setIdTciudadano(int idTciudadano) {
        this.idTciudadano = idTciudadano;
    }

    public int getIdTcamionRecolector() {
        return idTcamionRecolector;
    }

    public void setIdTcamionRecolector(int idTcamionRecolector) {
        this.idTcamionRecolector = idTcamionRecolector;
    }
}

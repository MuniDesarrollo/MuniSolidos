package com.example.carlin.munisolidos.Encapsulamiento;

import java.util.Date;

/**
 * Created by CARLIN on 17/02/2018.
 */

public class ReporteReciduo {


    private String fechaReportado;
    private int estado;
    private String fechaRecogido;
    private String imagen;
    private String rutaImagen;
    private double latitud;
    private double longitud;
    private  String descripcion;
    private  int idTciudadano;
    private  int idTcamionRecolector;

    public ReporteReciduo(String fechaReportado, int estado, String imagen,  String descripcion) {
        this.fechaReportado = fechaReportado;
        this.estado = estado;

        this.imagen = imagen;

        this.descripcion = descripcion;

    }
    public String getFechaReportado() {
        return fechaReportado;
    }

    public void setFechaReportado(String fechaReportado) {
        this.fechaReportado = fechaReportado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaRecogido() {
        return fechaRecogido;
    }

    public void setFechaRecogido(String fechaRecogido) {
        this.fechaRecogido = fechaRecogido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
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

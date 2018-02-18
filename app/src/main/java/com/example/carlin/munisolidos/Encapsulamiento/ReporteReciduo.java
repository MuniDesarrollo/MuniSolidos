package com.example.carlin.munisolidos.Encapsulamiento;

import java.util.Date;

/**
 * Created by CARLIN on 17/02/2018.
 */

public class ReporteReciduo {


    private Date fechaReportado;
    private int estado;
    private Date fechaRecogido;
    private String imagen;
    private String rutaImagen;
    private double latitud;
    private double longitud;
    private  String descripcion;
    private  int idTciudadano;
    private  int idTcamionRecolector;

    public ReporteReciduo(Date fechaReportado, int estado, Date fechaRecogido, String imagen, String rutaImagen, double latitud, double longitud, String descripcion, int idTciudadano, int idTcamionRecolector) {
        this.fechaReportado = fechaReportado;
        this.estado = estado;
        this.fechaRecogido = fechaRecogido;
        this.imagen = imagen;
        this.rutaImagen = rutaImagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.idTciudadano = idTciudadano;
        this.idTcamionRecolector = idTcamionRecolector;
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

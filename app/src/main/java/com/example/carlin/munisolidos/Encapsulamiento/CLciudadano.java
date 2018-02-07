package com.example.carlin.munisolidos.Encapsulamiento;

/**
 * Created by INFO_LAPTOP on 7/02/2018.
 */

public class CLciudadano {

    //Atributos de la tabla ciudadano............

    private String dni;
    private  String nombre;
    private  String apellidos;
    private  String correo;
    private String usuario;
    private String contraseña;

    public CLciudadano(String _dni, String _nombre, String _apellido, String _correo, String _usuario, String _contrasenia)
    {
        this.setDni(_dni);
        this.setNombre(_nombre);
        this.setApellidos(_apellido);
        this.setCorreo(_correo);
        this.setUsuario(_usuario);
        this.setContraseña(_contrasenia);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

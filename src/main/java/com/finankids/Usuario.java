package com.finankids;

public abstract class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;

    public Usuario(int id, String nombre, String apellido, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    public abstract void mostrarInformacion();
}
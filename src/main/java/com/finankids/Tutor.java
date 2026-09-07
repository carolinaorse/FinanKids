package com.finankids;

public class Tutor extends Usuario {

    public Tutor(int idUsuario, String nombre, String apellido,
                 String email, String contrasena) {

        super(idUsuario, nombre, apellido, email, contrasena);
    }

    public void mostrarInformacion() {
        System.out.println("Tutor: " + getNombreCompleto());
        System.out.println("Email: " + getEmail());
    }
}
package com.finankids;

public class PruebaUsuarioDAO {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        boolean guardado = usuarioDAO.guardarUsuario(
                "Lucia",
                "Perez",
                "lucia.perez@finankids.com",
                "1234",
                "MENOR"
        );

        if (guardado) {
            System.out.println("USUARIO GUARDADO CORRECTAMENTE EN LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO GUARDAR EL USUARIO.");
        }
    }
}
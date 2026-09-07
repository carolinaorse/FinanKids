package com.finankids;

public class PruebaTutorDAO {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        boolean guardado = usuarioDAO.guardarUsuario(
                "Carolina",
                "Orse",
                "carolina.tutor@finankids.com",
                "1234",
                "TUTOR"
        );

        if (guardado) {
            System.out.println("TUTOR GUARDADO CORRECTAMENTE EN LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO GUARDAR EL TUTOR.");
        }
    }
}
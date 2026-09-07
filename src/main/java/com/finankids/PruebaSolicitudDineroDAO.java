package com.finankids;

public class PruebaSolicitudDineroDAO {

    public static void main(String[] args) {

        SolicitudDineroDAO solicitudDAO = new SolicitudDineroDAO();

        boolean guardada = solicitudDAO.guardarSolicitud(
                1,
                2,
                2000.00,
                "Comprar útiles escolares"
        );

        if (guardada) {
            System.out.println("SOLICITUD GUARDADA CORRECTAMENTE EN LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO GUARDAR LA SOLICITUD.");
        }
    }
}
package com.finankids;

public class PruebaMetaAhorroDAO {

    public static void main(String[] args) {

        MetaAhorroDAO metaDAO = new MetaAhorroDAO();

        boolean guardada = metaDAO.guardarMeta(
                1,
                "Bicicleta",
                50000.00,
                10000.00,
                "EN_PROGRESO"
        );

        if (guardada) {
            System.out.println("META DE AHORRO GUARDADA CORRECTAMENTE EN LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO GUARDAR LA META DE AHORRO.");
        }
    }
}
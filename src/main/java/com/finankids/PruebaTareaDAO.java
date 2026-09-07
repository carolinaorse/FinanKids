package com.finankids;

public class PruebaTareaDAO {

    public static void main(String[] args) {

        TareaDAO tareaDAO = new TareaDAO();

        boolean guardada = tareaDAO.guardarTarea(
                2,
                1,
                "Ordenar la habitación",
                "Ordenar y guardar los objetos personales de la habitación.",
                "PENDIENTE"
        );

        if (guardada) {
            System.out.println("TAREA GUARDADA CORRECTAMENTE EN LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO GUARDAR LA TAREA.");
        }
    }
}
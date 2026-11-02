
package com.finankids;

import com.finankids.dao.TareaDAO;

public class PruebaTareaDAO {

    public static void main(String[] args) {

        TareaDAO tareaDAO = new TareaDAO();

        boolean guardada = tareaDAO.guardarTarea(
            1,
            1,
            2,
            "Ordenar la habitacion",
            "Ordenar y guardar los objetos personales de la habitacion."
        );

        if (guardada) {
            System.out.println(
                "TAREA GUARDADA CORRECTAMENTE EN LA BASE DE DATOS."
            );
        } else {
            System.out.println(
                "NO SE PUDO GUARDAR LA TAREA."
            );
        }
    }
}

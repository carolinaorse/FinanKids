package com.finankids;

public class PruebaRecompensaDAO {

    public static void main(String[] args) {

        TareaDAO tareaDAO = new TareaDAO();

        boolean tareaGuardada = tareaDAO.guardarTarea(
                2,
                1,
                "Lavar el auto",
                "Lavar y limpiar el auto familiar",
                "PENDIENTE"
        );

        if (!tareaGuardada) {
            System.out.println("NO SE PUDO GUARDAR LA TAREA.");
            return;
        }

        System.out.println("TAREA GUARDADA CORRECTAMENTE.");

        RecompensaDAO recompensaDAO = new RecompensaDAO();

        boolean recompensaGuardada = recompensaDAO.guardarRecompensa(
                2,
                3000.00,
                "PENDIENTE"
        );

        if (recompensaGuardada) {
            System.out.println("RECOMPENSA GUARDADA CORRECTAMENTE.");
        } else {
            System.out.println("NO SE PUDO GUARDAR LA RECOMPENSA.");
        }
    }
}
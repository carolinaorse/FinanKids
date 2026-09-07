package com.finankids;

import java.time.LocalDateTime;

public class PruebaValidacionTarea {

    public static void main(String[] args) {

        int idTarea = 2;
        int idRecompensa = 1;
        int idMenor = 1;
        double montoRecompensa = 3000.00;

        TareaDAO tareaDAO = new TareaDAO();
        RecompensaDAO recompensaDAO = new RecompensaDAO();
        MovimientoDAO movimientoDAO = new MovimientoDAO();

        System.out.println("=== VALIDACIÓN DE TAREA FINANKIDS ===");

        // 1. El menor informa que realizó la tarea
        boolean tareaRealizada =
                tareaDAO.actualizarEstadoTarea(idTarea, "REALIZADA");

        if (!tareaRealizada) {
            System.out.println("No se pudo marcar la tarea como REALIZADA.");
            return;
        }

        System.out.println("Tarea marcada como REALIZADA.");

        // 2. El tutor aprueba la tarea
        boolean tareaAprobada =
                tareaDAO.actualizarEstadoTarea(idTarea, "APROBADA");

        if (!tareaAprobada) {
            System.out.println("No se pudo aprobar la tarea.");
            return;
        }

        System.out.println("Tarea APROBADA por el tutor.");

        // 3. Se acredita la recompensa asociada
        boolean recompensaAcreditada =
                recompensaDAO.actualizarEstadoRecompensa(
                        idRecompensa,
                        "ACREDITADA"
                );

        if (!recompensaAcreditada) {
            System.out.println("No se pudo acreditar la recompensa.");
            return;
        }

        System.out.println("Recompensa ACREDITADA.");

        // 4. Se registra el movimiento financiero
        boolean movimientoGuardado =
                movimientoDAO.guardarMovimiento(
                        idMenor,
                        "RECOMPENSA",
                        montoRecompensa,
                        "Recompensa por tarea: Lavar el auto",
                        LocalDateTime.now()
                );

        if (movimientoGuardado) {
            System.out.println(
                    "Movimiento de recompensa registrado correctamente."
            );
        } else {
            System.out.println(
                    "No se pudo registrar el movimiento de recompensa."
            );
        }

        System.out.println("=== FIN DE LA VALIDACIÓN ===");
    }
}
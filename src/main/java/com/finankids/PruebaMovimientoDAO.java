package com.finankids;

import java.time.LocalDateTime;

public class PruebaMovimientoDAO {

    public static void main(String[] args) {

        MovimientoDAO movimientoDAO = new MovimientoDAO();

        boolean guardado = movimientoDAO.guardarMovimiento(
                1,
                "ASIGNACION",
                1500.00,
                "Asignación de prueba desde Java",
                LocalDateTime.now()
        );

        if (guardado) {
            System.out.println("MOVIMIENTO GUARDADO CORRECTAMENTE EN LA BASE DE DATOS.");
        } else {
            System.out.println("NO SE PUDO GUARDAR EL MOVIMIENTO.");
        }
    }
}
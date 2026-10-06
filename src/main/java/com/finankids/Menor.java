package com.finankids;

import java.util.ArrayList;
import java.util.List;

public class Menor extends Usuario {

    private double saldo;
    private List<Movimiento> movimientos;

    public Menor(int idUsuario, String nombre, String apellido,
                 String email, String contrasena) {

        super(idUsuario, nombre, apellido, email, contrasena);
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void asignarDinero(double monto) {

        if (monto > 0) {

            saldo += monto;

            Movimiento movimiento = new Movimiento(
                    movimientos.size() + 1,
                    "ASIGNACION",
                    monto,
                    "Asignación recibida del tutor"
            );

            movimientos.add(movimiento);
        }
    }

    public void registrarGasto(double monto, String descripcion) {

        if (monto > 0 && monto <= saldo) {

            saldo -= monto;

            Movimiento movimiento = new Movimiento(
                    movimientos.size() + 1,
                    "GASTO",
                    monto,
                    descripcion
            );

            movimientos.add(movimiento);
        }
    }

    public boolean aportarAMeta(double monto, String nombreMeta) {

        if (monto > 0 && monto <= saldo) {

            saldo -= monto;

            Movimiento movimiento = new Movimiento(
                    movimientos.size() + 1,
                    "AHORRO",
                    monto,
                    "Aporte a la meta: " + nombreMeta
            );

            movimientos.add(movimiento);

            return true;
        }

        return false;
    }

    public void mostrarMovimientos() {

        System.out.println("--- Historial de movimientos ---");

        if (movimientos.isEmpty()) {
            System.out.println("No existen movimientos registrados.");
            return;
        }

        for (Movimiento movimiento : movimientos) {
            movimiento.mostrarMovimiento();
            System.out.println("------------------------------");
        }
    }
@Override
    public void mostrarInformacion() {
        System.out.println("Menor: " + getNombreCompleto());
        System.out.println("Email: " + getEmail());
        System.out.println("Saldo disponible: $" + saldo);
    }
}
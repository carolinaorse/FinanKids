package com.finankids;

import java.time.LocalDateTime;

public class Movimiento {

    private int idMovimiento;
    private String tipo;
    private double monto;
    private String descripcion;
    private LocalDateTime fecha;

    public Movimiento(int idMovimiento, String tipo,
                      double monto, String descripcion) {

        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void mostrarMovimiento() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Monto: $" + monto);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha: " + fecha);
    }
}
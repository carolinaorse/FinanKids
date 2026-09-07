package com.finankids;

public class Recompensa {

    private int idRecompensa;
    private int idTarea;
    private double monto;
    private String estado;

    public Recompensa(
            int idRecompensa,
            int idTarea,
            double monto,
            String estado) {

        this.idRecompensa = idRecompensa;
        this.idTarea = idTarea;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdRecompensa() {
        return idRecompensa;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public double getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }

    public void mostrarRecompensa() {
        System.out.println("Recompensa: $" + monto);
        System.out.println("Estado: " + estado);
    }
}
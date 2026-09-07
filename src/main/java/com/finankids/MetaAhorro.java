package com.finankids;

public class MetaAhorro {

    private int idMeta;
    private Menor menor;
    private String nombre;
    private double montoObjetivo;
    private double montoAhorrado;
    private String estado;

    public MetaAhorro(int idMeta, Menor menor, String nombre,
                      double montoObjetivo) {

        this.idMeta = idMeta;
        this.menor = menor;
        this.nombre = nombre;
        this.montoObjetivo = montoObjetivo;
        this.montoAhorrado = 0.0;
        this.estado = "EN_PROGRESO";
    }

    public int getIdMeta() {
        return idMeta;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMontoObjetivo() {
        return montoObjetivo;
    }

    public double getMontoAhorrado() {
        return montoAhorrado;
    }

    public String getEstado() {
        return estado;
    }

    public double calcularProgreso() {

        if (montoObjetivo <= 0) {
            return 0;
        }

        return (montoAhorrado / montoObjetivo) * 100;
    }

    public void realizarAporte(double monto) {

        if (monto <= 0 || estado.equals("ALCANZADA")) {
            return;
        }

        double montoPendiente = montoObjetivo - montoAhorrado;
        double montoAportar = Math.min(monto, montoPendiente);

        boolean aporteRealizado = menor.aportarAMeta(
                montoAportar,
                nombre
        );

        if (aporteRealizado) {

            montoAhorrado += montoAportar;

            if (montoAhorrado >= montoObjetivo) {
                montoAhorrado = montoObjetivo;
                estado = "ALCANZADA";
            }
        }
    }

    public double calcularMontoFaltante() {
        return montoObjetivo - montoAhorrado;
    }

    public void mostrarMeta() {

        System.out.println("Meta: " + nombre);
        System.out.println("Menor: " + menor.getNombreCompleto());
        System.out.println("Monto objetivo: $" + montoObjetivo);
        System.out.println("Monto ahorrado: $" + montoAhorrado);
        System.out.printf("Progreso: %.2f%%%n", calcularProgreso());
        System.out.println("Monto faltante: $" + calcularMontoFaltante());
        System.out.println("Estado: " + estado);
    }
}
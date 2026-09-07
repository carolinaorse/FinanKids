package com.finankids;

import java.time.LocalDateTime;

public class SolicitudDinero {

    private int idSolicitud;
    private Menor menor;
    private Tutor tutor;
    private double monto;
    private String motivo;
    private LocalDateTime fecha;
    private String estado;

    public SolicitudDinero(int idSolicitud, Menor menor, Tutor tutor,
                           double monto, String motivo) {

        this.idSolicitud = idSolicitud;
        this.menor = menor;
        this.tutor = tutor;
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public Menor getMenor() {
        return menor;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public double getMonto() {
        return monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void aprobar() {

        if (estado.equals("PENDIENTE")) {
            estado = "APROBADA";
            menor.asignarDinero(monto);
        }
    }

    public void rechazar() {

        if (estado.equals("PENDIENTE")) {
            estado = "RECHAZADA";
        }
    }

    public void mostrarSolicitud() {

        System.out.println("Solicitud N°: " + idSolicitud);
        System.out.println("Menor: " + menor.getNombreCompleto());
        System.out.println("Tutor: " + tutor.getNombreCompleto());
        System.out.println("Monto solicitado: $" + monto);
        System.out.println("Motivo: " + motivo);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha: " + fecha);
    }
}
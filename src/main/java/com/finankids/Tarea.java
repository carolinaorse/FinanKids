package com.finankids;

public class Tarea {

    private int idTarea;
    private int idTutor;
    private int idMenor;
    private String titulo;
    private String descripcion;
    private String estado;

    public Tarea(
            int idTarea,
            int idTutor,
            int idMenor,
            String titulo,
            String descripcion,
            String estado) {

        this.idTarea = idTarea;
        this.idTutor = idTutor;
        this.idMenor = idMenor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public int getIdMenor() {
        return idMenor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void mostrarTarea() {
        System.out.println("Tarea: " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Estado: " + estado);
    }
}
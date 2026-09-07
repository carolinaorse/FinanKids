package com.finankids;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        System.out.println("Probando conexión con FinanKids...");

        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {
            System.out.println("PRUEBA EXITOSA: Java está conectado con finankids_db.");
        } else {
            System.out.println("PRUEBA FALLIDA: no fue posible conectar con la base de datos.");
        }
    }
}
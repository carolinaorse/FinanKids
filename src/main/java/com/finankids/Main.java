package com.finankids;

public class Main {

    public static void main(String[] args) {

        System.out.println("==============================");
        System.out.println("          FINANKIDS");
        System.out.println("   Educación Financiera Familiar");
        System.out.println("==============================");
        System.out.println();

        Tutor tutor = new Tutor(
                1,
                "Carolina",
                "Orse",
                "carolina@finankids.com",
                "1234"
        );

        Menor menor = new Menor(
                2,
                "Mateo",
                "Orse",
                "mateo@finankids.com",
                "5678"
        );

        menor.asignarDinero(5000);
        menor.registrarGasto(1200, "Compra de merienda");

        System.out.println("--- Estado del menor antes de la solicitud ---");
        menor.mostrarInformacion();

        System.out.println();

        SolicitudDinero solicitud = new SolicitudDinero(
                1,
                menor,
                tutor,
                2500,
                "Comprar materiales para la escuela"
        );

        System.out.println("--- Solicitud creada ---");
        solicitud.mostrarSolicitud();

        System.out.println();
        System.out.println("--- Tutor aprueba la solicitud ---");

        solicitud.aprobar();

        System.out.println();
        solicitud.mostrarSolicitud();

        System.out.println();
        System.out.println("--- Estado del menor después de la aprobación ---");
        menor.mostrarInformacion();

        System.out.println();
        menor.mostrarMovimientos();

        System.out.println();
        System.out.println("--- Meta de ahorro ---");

        MetaAhorro meta = new MetaAhorro(
                1,
                menor,
                "Bicicleta",
                50000
        );

        meta.mostrarMeta();

        System.out.println();
        System.out.println("--- Después de realizar un aporte de $3000 ---");

meta.realizarAporte(3000);
meta.mostrarMeta();

System.out.println();
System.out.println("--- Estado del menor después del aporte ---");
menor.mostrarInformacion();

System.out.println();
menor.mostrarMovimientos();
    }
}
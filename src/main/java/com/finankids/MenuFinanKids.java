package com.finankids;

import java.util.Scanner;

public class MenuFinanKids {

private final Scanner scanner;
private final Menor menor;
private final Tutor tutor;
private final MetaAhorro meta;

public MenuFinanKids(Menor menor, Tutor tutor, MetaAhorro meta) {
    this.scanner = new Scanner(System.in);
    this.menor = menor;
    this.tutor = tutor;
    this.meta = meta;
}

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println();
            System.out.println("================================");
            System.out.println("          FINANKIDS");
            System.out.println("   Educacion Financiera Familiar");
            System.out.println("================================");
            System.out.println("1. Consultar informacion del menor");
            System.out.println("2. Consultar movimientos");
            System.out.println("3. Consultar meta de ahorro");
            System.out.println("4. Solicitar dinero");
            System.out.println("5. Registrar gasto");
            System.out.println("0. Salir");
            System.out.println("================================");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("--- Informacion del menor ---");
                        menor.mostrarInformacion();
                        break;

                    case 2:
                        System.out.println("--- Movimientos del menor ---");
                        menor.mostrarMovimientos();
                        break;

                    case 3:
                        System.out.println("--- Meta de ahorro ---");
                        meta.mostrarMeta();
                        break;

                    case 4:
                        System.out.println("--- Solicitud de dinero ---");

    try {
        System.out.print("Ingrese el monto solicitado: $");
        double montoSolicitado = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el motivo de la solicitud: ");
        String motivo = scanner.nextLine();

        if (montoSolicitado <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            break;
        }

        if (motivo.trim().isEmpty()) {
            System.out.println("El motivo no puede estar vacio.");
            break;
        }

        SolicitudDinero solicitud = new SolicitudDinero(
            1,
            menor,
            tutor,
            montoSolicitado,
            motivo
        );

        System.out.println();
        System.out.println("Solicitud creada correctamente.");
        solicitud.mostrarSolicitud();

    } catch (NumberFormatException e) {
        System.out.println("Error: el monto debe ser un numero valido.");
    }

    break;

                    case 5:
                        System.out.println("--- Registrar gasto ---");

    System.out.print("Ingrese el monto del gasto: $");
    double montoGasto;

    try {
        montoGasto = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("El monto ingresado no es valido.");
        break;
    }

    if (montoGasto <= 0) {
        System.out.println("El monto debe ser mayor que cero.");
        break;
    }

    if (montoGasto > menor.getSaldo()) {
        System.out.println("Saldo insuficiente.");
        System.out.println("Saldo disponible: $" + menor.getSaldo());
        break;
    }

    System.out.print("Ingrese la descripcion del gasto: ");
    String descripcionGasto = scanner.nextLine();

    if (descripcionGasto.trim().isEmpty()) {
        System.out.println("La descripcion no puede estar vacia.");
        break;
    }

    menor.registrarGasto(montoGasto, descripcionGasto);

    System.out.println();
    System.out.println("Gasto registrado correctamente.");
    System.out.println("Monto: $" + montoGasto);
    System.out.println("Descripcion: " + descripcionGasto);
    System.out.println("Saldo restante: $" + menor.getSaldo());
    break;

                    case 0:
                        System.out.println("Gracias por utilizar FinanKids.");
                        break;

                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un numero.");
                opcion = -1;
            }

        } while (opcion != 0);
    }
}

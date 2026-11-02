

package com.finankids.view;

import java.util.Scanner;

public class FinanKidsVista {

    private final Scanner scanner;

    public FinanKidsVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("================================");
        System.out.println("       BIENVENIDO A FINANKIDS    ");
        System.out.println("================================");
        System.out.println("Educacion financiera familiar");
    }

    public void mostrarMenu() {
        System.out.println("\n===== MENU FINANKIDS =====");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Consultar usuarios");
        System.out.println("3. Vincular usuario a familia");
        System.out.println("4. Gestionar tareas");
        System.out.println("5. Consultar movimientos");
        System.out.println("0. Salir");
    }

    public int solicitarOpcion() {
        return solicitarEntero("Seleccione una opcion: ");
    }

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int solicitarEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            try {
                return Integer.parseInt(
                    scanner.nextLine().trim()
                );
            } catch (NumberFormatException e) {
                mostrarError(
                    "Debe ingresar un numero entero valido."
                );
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }

    public void mostrarFormularioRegistro() {
        System.out.println("\n=== REGISTRO DE USUARIO ===");
        System.out.println(
            "Complete los datos del nuevo usuario."
        );
    }

    public String solicitarNombre() {
        return solicitarTexto("Nombre: ");
    }

    public String solicitarApellido() {
        return solicitarTexto("Apellido: ");
    }

    public String solicitarEmail() {
        return solicitarTexto("Correo electronico: ");
    }

    public String solicitarContrasena() {
        return solicitarTexto("Contrasena: ");
    }

    public String solicitarFechaNacimiento() {
        return solicitarTexto(
            "Fecha de nacimiento (AAAA-MM-DD): "
        );
    }

    public void mostrarMenuTareas() {
        System.out.println("\n===== GESTION DE TAREAS =====");
        System.out.println("1. Registrar nueva tarea");
        System.out.println("2. Actualizar estado de tarea");
        System.out.println("0. Volver al menu principal");
    }

    public void mostrarFormularioTarea() {
        System.out.println("\n=== REGISTRO DE TAREA ===");
    }
}

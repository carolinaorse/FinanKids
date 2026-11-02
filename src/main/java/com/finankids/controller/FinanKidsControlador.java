
package com.finankids.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.finankids.view.FinanKidsVista;
import com.finankids.dao.UsuarioDAO;
import com.finankids.dao.UsuarioGrupoDAO;
import com.finankids.dao.TareaDAO;

public class FinanKidsControlador {

    private final FinanKidsVista vista;
    private final UsuarioDAO usuarioDAO;
    private final UsuarioGrupoDAO usuarioGrupoDAO;
    private final TareaDAO tareaDAO;

    public FinanKidsControlador(FinanKidsVista vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioGrupoDAO = new UsuarioGrupoDAO();
        this.tareaDAO = new TareaDAO();
    }

    public void iniciar() {

        int opcion;
        vista.mostrarBienvenida();

        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;

                case 2:
                    usuarioDAO.listarUsuarios();
                    break;

                case 3:
                    vincularUsuarioAFamilia();
                    break;

                case 4:
                    gestionarTareas();
                    break;

                case 5:
                    vista.mostrarMensaje(
                        "Consultar movimientos: pendiente de integrar."
                    );
                    break;

                case 0:
                    vista.mostrarMensaje(
                        "Gracias por utilizar FinanKids."
                    );
                    break;

                default:
                    vista.mostrarError(
                        "Opcion no valida. Intente nuevamente."
                    );
            }

        } while (opcion != 0);
    }

    private void registrarUsuario() {

        vista.mostrarFormularioRegistro();

        String nombre = vista.solicitarNombre();
        String apellido = vista.solicitarApellido();
        String email = vista.solicitarEmail();
        String contrasena = vista.solicitarContrasena();
        String fechaTexto = vista.solicitarFechaNacimiento();

        if (nombre.isBlank() || apellido.isBlank()
                || email.isBlank() || contrasena.isBlank()) {
            vista.mostrarError(
                "Todos los campos son obligatorios."
            );
            return;
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            vista.mostrarError(
                "El correo electronico no es valido."
            );
            return;
        }

        if (contrasena.length() < 8) {
            vista.mostrarError(
                "La contrasena debe tener al menos 8 caracteres."
            );
            return;
        }

        LocalDate fechaNacimiento;

        try {
            fechaNacimiento = LocalDate.parse(fechaTexto);
        } catch (DateTimeParseException e) {
            vista.mostrarError(
                "Fecha invalida. Utilice AAAA-MM-DD."
            );
            return;
        }

        if (fechaNacimiento.isAfter(LocalDate.now())) {
            vista.mostrarError(
                "La fecha de nacimiento no puede ser futura."
            );
            return;
        }

        boolean resultado = usuarioDAO.guardarUsuario(
            nombre,
            apellido,
            email,
            contrasena,
            fechaNacimiento
        );

        if (resultado) {
            vista.mostrarMensaje(
                "Usuario registrado correctamente."
            );
            vista.mostrarMensaje(
                "Para asignarle un rol, utilice la opcion 3."
            );
        } else {
            vista.mostrarError(
                "No se pudo registrar el usuario."
            );
        }
    }

    private void vincularUsuarioAFamilia() {

        vista.mostrarMensaje(
            "\n=== VINCULAR USUARIO A FAMILIA ==="
        );

        int idUsuario = vista.solicitarEntero(
            "Ingrese ID del usuario: "
        );

        int idGrupo = vista.solicitarEntero(
            "Ingrese ID del grupo familiar: "
        );

        String rol = vista.solicitarTexto(
            "Ingrese rol (TUTOR/MENOR): "
        ).toUpperCase();

        if (idUsuario <= 0 || idGrupo <= 0) {
            vista.mostrarError(
                "Los identificadores deben ser positivos."
            );
            return;
        }

        if (!rol.equals("TUTOR") && !rol.equals("MENOR")) {
            vista.mostrarError(
                "El rol debe ser TUTOR o MENOR."
            );
            return;
        }

        boolean resultado = usuarioGrupoDAO.vincularUsuario(
            idUsuario, idGrupo, rol
        );

        if (resultado) {
            vista.mostrarMensaje(
                "Usuario vinculado correctamente."
            );
        } else {
            vista.mostrarError(
                "No se pudo realizar la vinculacion."
            );
        }
    }

    private void gestionarTareas() {

        int opcion;

        do {
            vista.mostrarMenuTareas();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case 1:
                    registrarTarea();
                    break;

                case 2:
                    actualizarEstadoTarea();
                    break;

                case 0:
                    vista.mostrarMensaje(
                        "Volviendo al menu principal."
                    );
                    break;

                default:
                    vista.mostrarError(
                        "Opcion de tareas no valida."
                    );
            }

        } while (opcion != 0);
    }

    private void registrarTarea() {

        vista.mostrarFormularioTarea();

        int idGrupo = vista.solicitarEntero(
            "ID del grupo familiar: "
        );

        int idTutor = vista.solicitarEntero(
            "ID del tutor: "
        );

        int idMenor = vista.solicitarEntero(
            "ID del menor: "
        );

        String titulo = vista.solicitarTexto(
            "Titulo de la tarea: "
        );

        String descripcion = vista.solicitarTexto(
            "Descripcion de la tarea: "
        );

        boolean resultado = tareaDAO.guardarTarea(
            idGrupo,
            idTutor,
            idMenor,
            titulo,
            descripcion
        );

        if (resultado) {
            vista.mostrarMensaje(
                "Tarea registrada correctamente en estado PENDIENTE."
            );
        } else {
            vista.mostrarError(
                "No se pudo registrar la tarea. "
                + "Verifique los datos y los roles familiares."
            );
        }
    }

    private void actualizarEstadoTarea() {

        vista.mostrarMensaje(
            "\n=== ACTUALIZAR ESTADO DE TAREA ==="
        );

        int idTarea = vista.solicitarEntero(
            "ID de la tarea: "
        );

        String nuevoEstado = vista.solicitarTexto(
            "Nuevo estado (PENDIENTE/INFORMADA/"
            + "VALIDADA/RECHAZADA): "
        );

        boolean resultado = tareaDAO.actualizarEstadoTarea(
            idTarea,
            nuevoEstado
        );

        if (resultado) {
            vista.mostrarMensaje(
                "Estado de la tarea actualizado correctamente."
            );
        } else {
            vista.mostrarError(
                "No se pudo actualizar el estado de la tarea."
            );
        }
    }
}

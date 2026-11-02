

package com.finankids.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.finankids.ConexionBD;

public class TareaDAO {

    public boolean guardarTarea(
            int idGrupo,
            int idTutor,
            int idMenor,
            String titulo,
            String descripcion) {

        if (idGrupo <= 0 || idTutor <= 0 || idMenor <= 0
                || idTutor == idMenor
                || titulo == null || titulo.isBlank()
                || titulo.length() > 100
                || (descripcion != null
                    && descripcion.length() > 200)) {

            System.out.println("Error: datos de tarea no validos.");
            return false;
        }

        String sql = """
            INSERT INTO tarea
            (id_grupo, id_tutor, id_menor,
             titulo, descripcion, estado)
            SELECT ?, ?, ?, ?, ?, 'PENDIENTE'
            WHERE EXISTS (
                SELECT 1
                FROM usuario_grupo
                WHERE id_grupo = ?
                  AND id_usuario = ?
                  AND rol = 'TUTOR'
                  AND estado = 'ACTIVO'
            )
            AND EXISTS (
                SELECT 1
                FROM usuario_grupo
                WHERE id_grupo = ?
                  AND id_usuario = ?
                  AND rol = 'MENOR'
                  AND estado = 'ACTIVO'
            )
            """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement statement =
                     conexion.prepareStatement(sql)) {

            statement.setInt(1, idGrupo);
            statement.setInt(2, idTutor);
            statement.setInt(3, idMenor);
            statement.setString(4, titulo.trim());
            statement.setString(5, descripcion);

            statement.setInt(6, idGrupo);
            statement.setInt(7, idTutor);
            statement.setInt(8, idGrupo);
            statement.setInt(9, idMenor);

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas == 0) {
                System.out.println(
                    "No se pudo registrar la tarea: "
                    + "verifique los integrantes y sus roles."
                );
            }

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar la tarea:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean actualizarEstadoTarea(
            int idTarea,
            String nuevoEstado) {

        if (idTarea <= 0 || nuevoEstado == null
                || nuevoEstado.isBlank()) {
            return false;
        }

        String estado = nuevoEstado.trim().toUpperCase();

        if (!estado.equals("PENDIENTE")
                && !estado.equals("INFORMADA")
                && !estado.equals("VALIDADA")
                && !estado.equals("RECHAZADA")) {

            System.out.println("Estado de tarea no valido.");
            return false;
        }

        String sql = """
            UPDATE tarea
            SET estado = ?
            WHERE id_tarea = ?
            """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement statement =
                     conexion.prepareStatement(sql)) {

            statement.setString(1, estado);
            statement.setInt(2, idTarea);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                "Error al actualizar el estado de la tarea:"
            );
            System.out.println(e.getMessage());
            return false;
        }
    }
}

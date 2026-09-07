package com.finankids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TareaDAO {

    public boolean guardarTarea(
            int idTutor,
            int idMenor,
            String titulo,
            String descripcion,
            String estado) {

        String sql = """
                INSERT INTO tareas
                (id_tutor, id_menor, titulo, descripcion, estado)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setInt(1, idTutor);
            statement.setInt(2, idMenor);
            statement.setString(3, titulo);
            statement.setString(4, descripcion);
            statement.setString(5, estado);

            int filasInsertadas = statement.executeUpdate();

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

    String sql = """
            UPDATE tareas
            SET estado = ?
            WHERE id_tarea = ?
            """;

    try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql)
    ) {

        statement.setString(1, nuevoEstado);
        statement.setInt(2, idTarea);

        int filasActualizadas = statement.executeUpdate();

        return filasActualizadas > 0;

    } catch (SQLException e) {

        System.out.println("Error al actualizar el estado de la tarea:");
        System.out.println(e.getMessage());

        return false;
    }
}
}
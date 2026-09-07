package com.finankids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecompensaDAO {

    public boolean guardarRecompensa(
            int idTarea,
            double monto,
            String estado) {

        String sql = """
                INSERT INTO recompensas
                (id_tarea, monto, estado)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setInt(1, idTarea);
            statement.setDouble(2, monto);
            statement.setString(3, estado);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar la recompensa:");
            System.out.println(e.getMessage());

            return false;
        }
    }
    public boolean actualizarEstadoRecompensa(
        int idRecompensa,
        String nuevoEstado) {

    String sql = """
            UPDATE recompensas
            SET estado = ?
            WHERE id_recompensa = ?
            """;

    try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql)
    ) {

        statement.setString(1, nuevoEstado);
        statement.setInt(2, idRecompensa);

        int filasActualizadas = statement.executeUpdate();

        return filasActualizadas > 0;

    } catch (SQLException e) {

        System.out.println("Error al actualizar el estado de la recompensa:");
        System.out.println(e.getMessage());

        return false;
    }
}
}
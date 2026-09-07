package com.finankids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MovimientoDAO {

    public boolean guardarMovimiento(
            int idUsuario,
            String tipo,
            double monto,
            String descripcion,
            LocalDateTime fecha) {

        String sql = """
                INSERT INTO movimientos
                (id_usuario, tipo, monto, descripcion, fecha)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setInt(1, idUsuario);
            statement.setString(2, tipo);
            statement.setDouble(3, monto);
            statement.setString(4, descripcion);
            statement.setObject(5, fecha);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar el movimiento:");
            System.out.println(e.getMessage());

            return false;
        }
    }
}
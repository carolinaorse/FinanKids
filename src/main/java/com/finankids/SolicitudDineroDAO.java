package com.finankids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SolicitudDineroDAO {

    public boolean guardarSolicitud(
            int idMenor,
            int idTutor,
            double monto,
            String motivo) {

        String sql = """
                INSERT INTO solicitudes_dinero
                (id_menor, id_tutor, monto, motivo)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setInt(1, idMenor);
            statement.setInt(2, idTutor);
            statement.setDouble(3, monto);
            statement.setString(4, motivo);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar la solicitud de dinero:");
            System.out.println(e.getMessage());

            return false;
        }
    }
}
package com.finankids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MetaAhorroDAO {

    public boolean guardarMeta(
            int idMenor,
            String nombre,
            double montoObjetivo,
            double montoAhorrado,
            String estado) {

        String sql = """
                INSERT INTO metas_ahorro
                (id_menor, nombre, monto_objetivo, monto_ahorrado, estado)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setInt(1, idMenor);
            statement.setString(2, nombre);
            statement.setDouble(3, montoObjetivo);
            statement.setDouble(4, montoAhorrado);
            statement.setString(5, estado);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar la meta de ahorro:");
            System.out.println(e.getMessage());

            return false;
        }
    }
}
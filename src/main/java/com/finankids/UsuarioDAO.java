package com.finankids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean guardarUsuario(
            String nombre,
            String apellido,
            String email,
            String contrasena,
            String tipoUsuario) {

        String sql = """
                INSERT INTO usuarios
                (nombre, apellido, email, contrasena, tipo_usuario)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);
            statement.setString(4, contrasena);
            statement.setString(5, tipoUsuario);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar el usuario:");
            System.out.println(e.getMessage());

            return false;
        }
    }
}
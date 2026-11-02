
package com.finankids.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.finankids.ConexionBD;

public class UsuarioGrupoDAO {

    public boolean vincularUsuario(
            int idUsuario,
            int idGrupo,
            String rol) {

        if (!"TUTOR".equals(rol) && !"MENOR".equals(rol)) {
            System.out.println("Rol no válido.");
            return false;
        }

        String sql = """
            INSERT INTO usuario_grupo
            (id_usuario, id_grupo, rol, fecha_vinculacion, estado)
            VALUES (?, ?, ?, CURRENT_TIMESTAMP, 'ACTIVO')
            """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement statement =
                     conexion.prepareStatement(sql)) {

            statement.setInt(1, idUsuario);
            statement.setInt(2, idGrupo);
            statement.setString(3, rol);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println(
                "Error al vincular usuario con grupo familiar:"
            );
            System.out.println(e.getMessage());
            return false;
        }
    }
}


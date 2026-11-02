

package com.finankids.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

import com.finankids.ConexionBD;

public class UsuarioDAO {

    private static final int ITERACIONES = 600000;
    private static final int LONGITUD_CLAVE = 256;

    public boolean guardarUsuario(
            String nombre,
            String apellido,
            String email,
            String contrasena,
            LocalDate fechaNacimiento) {

        if (nombre == null || nombre.isBlank()
                || apellido == null || apellido.isBlank()
                || email == null || email.isBlank()
                || contrasena == null || contrasena.isBlank()
                || fechaNacimiento == null
                || fechaNacimiento.isAfter(LocalDate.now())) {

            System.out.println(
                "Error: los datos del usuario no son validos."
            );
            return false;
        }

        String sql = """
            INSERT INTO usuario
            (nombre, apellido, email, password_hash, fecha_nacimiento)
            VALUES (?, ?, ?, ?, ?)
            """;

        try {
            String hash = generarHash(contrasena);

            try (Connection conexion = ConexionBD.conectar();
                 PreparedStatement statement =
                         conexion.prepareStatement(sql)) {

                statement.setString(1, nombre.trim());
                statement.setString(2, apellido.trim());
                statement.setString(3, email.trim());
                statement.setString(4, hash);
                statement.setDate(
                    5,
                    java.sql.Date.valueOf(fechaNacimiento)
                );

                return statement.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Error al guardar el usuario:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Compatibilidad temporal con pruebas anteriores.
    // El rol se administra en usuario_grupo.
    @Deprecated
    public boolean guardarUsuario(
            String nombre,
            String apellido,
            String email,
            String contrasena,
            String tipoUsuario) {

        return guardarUsuario(
            nombre,
            apellido,
            email,
            contrasena,
            LocalDate.of(1990, 1, 1)
        );
    }

    private String generarHash(String contrasena)
            throws Exception {

        byte[] sal = new byte[16];
        new SecureRandom().nextBytes(sal);

        KeySpec especificacion = new PBEKeySpec(
            contrasena.toCharArray(),
            sal,
            ITERACIONES,
            LONGITUD_CLAVE
        );

        SecretKeyFactory fabrica =
            SecretKeyFactory.getInstance(
                "PBKDF2WithHmacSHA256"
            );

        byte[] hash =
            fabrica.generateSecret(especificacion).getEncoded();

        return "pbkdf2_sha256$"
            + ITERACIONES + "$"
            + Base64.getEncoder().encodeToString(sal)
            + "$"
            + Base64.getEncoder().encodeToString(hash);
    }

    public void listarUsuarios() {

        String sql = """
            SELECT id_usuario, nombre, apellido, email, estado
            FROM usuario
            ORDER BY id_usuario
            """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement statement =
                     conexion.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            System.out.println("\n=== USUARIOS REGISTRADOS ===");

            while (resultado.next()) {

                System.out.println(
                    "ID: " + resultado.getInt("id_usuario")
                    + " | Nombre: " + resultado.getString("nombre")
                    + " " + resultado.getString("apellido")
                    + " | Email: " + resultado.getString("email")
                    + " | Estado: " + resultado.getString("estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuarios:");
            System.out.println(e.getMessage());
        }
    }
}

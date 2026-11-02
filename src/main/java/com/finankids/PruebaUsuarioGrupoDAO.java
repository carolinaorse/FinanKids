
package com.finankids;

import com.finankids.dao.UsuarioGrupoDAO;

public class PruebaUsuarioGrupoDAO {

    public static void main(String[] args) {

        UsuarioGrupoDAO dao = new UsuarioGrupoDAO();

        boolean resultado = dao.vincularUsuario(
            2,       // ID del usuario menor
            2,       // ID del grupo familiar
            "MENOR"  // Rol
        );

        if (resultado) {
            System.out.println(
                "VINCULACION GUARDADA CORRECTAMENTE EN LA BASE DE DATOS."
            );
        } else {
            System.out.println(
                "NO SE PUDO VINCULAR EL USUARIO AL GRUPO FAMILIAR."
            );
        }
    }
}


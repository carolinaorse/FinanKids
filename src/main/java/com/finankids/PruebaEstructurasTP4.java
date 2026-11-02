
package com.finankids;

import java.util.ArrayList;

public class PruebaEstructurasTP4 {

    public static void main(String[] args) {

        String[] categorias = {
            "Educacion",
            "Tecnologia",
            "Recreacion"
        };

        ArrayList<String> metas = new ArrayList<>();

        metas.add("Comprar una bicicleta");
        metas.add("Viaje de egresados");
        metas.add("Comprar un celular");

        System.out.println("=== FINANKIDS ===");
        System.out.println("CATEGORIAS DE AHORRO:");

        for (String categoria : categorias) {
            System.out.println("- " + categoria);
        }

        System.out.println("\nMETAS DE AHORRO:");

        for (String meta : metas) {
            System.out.println("- " + meta);
        }

        System.out.println(
            "\nTotal de metas: " + metas.size()
        );
    }
}

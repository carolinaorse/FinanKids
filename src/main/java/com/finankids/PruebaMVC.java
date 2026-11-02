
package com.finankids;

import com.finankids.view.FinanKidsVista;
import com.finankids.controller.FinanKidsControlador;

public class PruebaMVC {

    public static void main(String[] args) {

        FinanKidsVista vista = new FinanKidsVista();

        FinanKidsControlador controlador =
                new FinanKidsControlador(vista);

        controlador.iniciar();
    }
}


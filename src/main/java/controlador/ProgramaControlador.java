package controlador;

import modelo.enums.MenuEnum;
import vista.ProgramaVista;

public class ProgramaControlador {
    private final ProgramaVista vista;

    public ProgramaControlador(ProgramaVista vista) {
        this.vista = vista;
    }

    public void ejecuta() {
        MenuEnum opcion;
        while (true) {
            vista.mostrarMenu();
            int seleccion = solicitarInt("\nIntroduce una opción: ", 0, MenuEnum.values().length - 1, false);
            opcion = MenuEnum.values()[seleccion];
            switch (opcion) {
                case ELIMINAR_PRODUCTO -> System.out.println();
                case SALIR -> {
                    vista.mensaje("Saliendo del programa ....");
                    cerrarAplicacion();
                    return;
                }
            }
        }
    }

    private void cerrarAplicacion() {

        vista.mensaje("Finalizando la conexión a MySQL");
    }

    private int solicitarInt(String mensaje, int min, int max,boolean permitirNulo) {
        while (true) {
            String input = vista.solicitarEntrada(mensaje);
            if (permitirNulo) {
                if (input.isBlank()) return -1;
            }
            try {
                int valor = Integer.parseInt(input);
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    vista.mensaje("!!! ERROR !!!  El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                vista.mensaje("!!! ERROR !!!  Introduce un número entero válido.");
            }
        }
    }

//    public void iniciar() {
//
//    }
}

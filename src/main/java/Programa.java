import controlador.ProgramaControlador;
import vista.ProgramaVista;


public class Programa {
    static void main(String[] args) {
        ProgramaVista vista = new ProgramaVista();
        ProgramaControlador controlador = new ProgramaControlador(vista);

        controlador.ejecuta();
    }
}



package Vista;

import Controlador.excepciones.SeTerminaronLasFasesError;
import javafx.scene.control.Button;

public class EstadosJuegoBoton extends Button
{
    private Button botonFinDeTurno;
    private Button botonFinDeFase;
    private Vista vista;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public EstadosJuegoBoton(Vista vista)
    {
        this.vista = vista;
        this.botonFinDeFase = this.crearBotonAvanzarProximaFase();
        this.botonFinDeTurno = this.crearBotonTerminarTurno();
    }

    private Button crearBotonTerminarTurno()
    {
        Button boton = new Button("TERMINAR TURNO");

        boton.setOnAction(e -> terminarTurnoBtn_click());

        return boton;
    }

    private Button crearBotonAvanzarProximaFase()
    {
        Button boton = new Button("AVANZAR A PROXIMA FASE");

        boton.setOnAction(e -> avanzarProximaFaseBtn_click());

        return boton;
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    public Button getBotonFinDeTurno()
    {

        return this.botonFinDeTurno;
    }

    public Button getBotonFinDeFase()
    {

        return this.botonFinDeFase;
    }

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void terminarTurnoBtn_click()
    {

        this.vista.getControlador().terminarTurno();
        this.vista.mostrarJugadorActual();
        this.vista.mostrarFaseActual();
    }

    private void avanzarProximaFaseBtn_click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias -> implementar
        // múltiples catch.
        try
        {
            this.vista.getControlador().avanzarFase();
        } catch (SeTerminaronLasFasesError seTerminaronLasFasesError)
        {
            this.vista.getControlador().terminarTurno();
            this.vista.mostrarJugadorActual();
        }
        this.vista.mostrarFaseActual();
    }
}

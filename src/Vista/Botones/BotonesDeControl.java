package Vista.Botones;

import Controlador.excepciones.SeTerminaronLasFases;
import Vista.Vista;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BotonesDeControl extends Button
{
    private Button botonFinDeTurno;
    private Button botonFinDeFase;
    private Stage stage;
    private Vista vista;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public BotonesDeControl(Stage stage, Vista vista)
    {

        this.stage = stage;
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

        this.vista.obtenerControlador().terminarTurno();
        this.vista.mostrarJugadorActual();
        this.vista.mostrarFaseActual();
    }

    private void avanzarProximaFaseBtn_click()
    {
        try
        {
            this.vista.obtenerControlador().avanzarProximaFase();
        } catch (SeTerminaronLasFases seTerminaronLasFases)
        {
            this.vista.obtenerControlador().terminarTurno();
            this.vista.mostrarJugadorActual();
        }
        this.vista.mostrarFaseActual();
    }
}

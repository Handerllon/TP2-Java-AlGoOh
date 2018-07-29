package Vista;

import Controlador.excepciones.SeTerminaronLasFases;
import Modelo.Jugador;
import Modelo.carta.excepciones.ManoLlena;
import javafx.scene.control.Button;

public class EstadosJuegoBotones extends Button
{
    private Button botonFinDeTurno;
    private Button botonFinDeFase;
    private Vista vista;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public EstadosJuegoBotones(Vista vista)
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
    private void avanzarProximaFaseBtn_click()
    {
        try
        {
            this.vista.getControlador().avanzarFase();
        } catch (SeTerminaronLasFases seTerminaronLasFases)
        {
            terminarTurnoBtn_click();
        }
    }

    private void terminarTurnoBtn_click()
    {
        this.vista.getControlador().terminarTurno();
        this.vista.mostrarJugadorActual();
        Jugador jugadorActual = this.vista.getControlador().getJugadorActual();
        try
        {
            this.vista.getControlador().tomarCarta(jugadorActual);
        } catch (ManoLlena manoLlena)
        {
            this.vista.avisoManoLlena(manoLlena);
        }
    }
}

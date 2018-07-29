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
        Button boton = new Button("TERMINAR FASE");

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
        
        this.botonFinDeFase = this.crearBotonAvanzarProximaFase();
    }
    /*
    private Button actualizarBotonTerminarTurno()
    {
        Button boton = new Button("TERMINAR TURNO DE " + this.vista.getControlador().getNombreOponente());

        boton.setOnAction(e -> terminarTurnoBtn_click());

        return boton;
    }
    */
    private void terminarTurnoBtn_click()
    {
        this.vista.getControlador().terminarTurno();
        Jugador jugadorActual = this.vista.getControlador().getJugadorActual();
        try
        {
            this.vista.getControlador().tomarCarta(jugadorActual);
        } catch (ManoLlena manoLlena)
        {
            this.vista.avisoManoLlena(manoLlena);
        }
        //this.botonFinDeTurno = this.actualizarBotonTerminarTurno();
    }

}

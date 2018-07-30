package Vista.estadosJuego;

import Controlador.excepciones.SeTerminaronLasFases;
import Vista.Vista;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;

import java.net.URL;

public class EstadosJuegoBotones extends Button
{
    private Button botonFinDeTurno;
    private Button botonFinDeFase;
    private Vista vista;
    private AudioClip audioClipPhaseChange, audioClipTurnChange;
    private double cardPhaseChangeVolume = 0.3;
    private double cardTurnChangeVolume = 0.3;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public EstadosJuegoBotones(Vista vista)
    {
        this.vista = vista;
        this.botonFinDeFase = this.crearBotonAvanzarProximaFase();
        this.botonFinDeTurno = this.crearBotonTerminarTurno();

        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/phase_change.wav");
        this.audioClipPhaseChange = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipPhaseChange.setVolume(cardPhaseChangeVolume);

        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/turn_change.wav");
        this.audioClipTurnChange = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipTurnChange.setVolume(cardTurnChangeVolume);
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
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().avanzarFase();
        } catch (SeTerminaronLasFases seTerminaronLasFases)
        {
            thrown = true;
            terminarTurnoBtn_click();
        }
        if (!thrown)
        {
            this.audioClipPhaseChange.play();
        }
    }

    private void terminarTurnoBtn_click()
    {
        this.audioClipTurnChange.play();
        this.vista.getControlador().terminarTurno();
    }
}

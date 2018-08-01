package Vista.estadosJuego;

import Controlador.excepciones.SeTerminaronLasFases;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.net.URL;

public class EstadosJuegoBotones extends Button
{
    private static String direccion_backgroundPane = "resources/imagenes/end.jpg";
    private Vista vista;
    private AudioClip audioClipPhaseChange, audioClipTurnChange;
    private double cardPhaseChangeVolume = 0.3;
    private double cardTurnChangeVolume = 0.3;
    private GridPane grid;
    private VBox paneButtons;
    private double porcentajeDeTamanioDeFuente = 0.02;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public EstadosJuegoBotones(Vista vista)
    {
        this.vista = vista;

        this.inicializarEscena();

        // -------------------------------
        // Multimedia.
        // -------------------------------
        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/phase_change.wav");
        this.audioClipPhaseChange = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipPhaseChange.setVolume(cardPhaseChangeVolume);

        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/turn_change.wav");
        this.audioClipTurnChange = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipTurnChange.setVolume(cardTurnChangeVolume);
    }

    private void inicializarEscena()
    {
        this.grid = new GridPane();

        int fontSizeInPoints = (int) Math.floor(this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente);

        // -------------------------------
        // Buttons definitions.
        // -------------------------------
        Button btnEndTurn = new Button("TERMINAR TURNO");
        btnEndTurn.setMinWidth(75);
        btnEndTurn.setAlignment(Pos.CENTER);
        btnEndTurn.setStyle("-fx-font-weight: bolder; -fx-font-size: " + fontSizeInPoints + "pt");
        btnEndTurn.setOnAction(e -> terminarTurnoBtn_click());

        Button btnEndPhase = new Button("TERMINAR FASE");
        btnEndPhase.setMinWidth(75);
        btnEndPhase.setAlignment(Pos.CENTER);
        btnEndPhase.setStyle("-fx-font-weight: bolder; -fx-font-size: " + fontSizeInPoints + "pt");
        btnEndPhase.setOnAction(e -> avanzarProximaFaseBtn_click());

        double btnSeparationInPixels = 20;
        this.paneButtons = new VBox(btnSeparationInPixels, btnEndTurn, btnEndPhase);

        String color = "0xa6b751";
        double transparencia = 0.8;
        Background backgroundBotones = new Background(new BackgroundFill(Color.web(color,transparencia), CornerRadii.EMPTY, Insets.EMPTY));
        this.paneButtons.setBackground(backgroundBotones);

        this.paneButtons.setAlignment(Pos.CENTER);
        this.paneButtons.setStyle(
                "-fx-border-color: navy, black;" +
                        "-fx-border-width: 5, 5;" +
                        "-fx-border-radius: 0, 0;" +
                        "-fx-border-insets: 0, 5;" +
                        "-fx-border-style: solid inside, dotted outside;");

        // -------------------------------
        // Setting grid.
        // -------------------------------
        this.grid.setPadding(new Insets(10));
        this.grid.setHgap(btnSeparationInPixels);
        this.grid.setVgap(btnSeparationInPixels);
        this.grid.setMinWidth(500);

        this.grid.addRow(0, paneButtons);
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    public VBox getPaneButtons()
    {
        return this.paneButtons;
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

package Vista.carta;

import Modelo.Jugador;
import Modelo.ModeloObservable;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

import java.net.URL;

public class MazosVista implements ObservadorDeModelo
{
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    // Se uso como base una resolucion de 1920x1080
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private double porcentajeDeTamanioDeFuente = 0.02;
    private Vista vista;
    private ModeloObservable modelo;
    private AudioClip audioClipCardDraw;
    private double cardDrawVolume = 1;
    private StackPane stackJugador1, stackJugador2;
    private Jugador jugador1, jugador2;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public MazosVista(Vista vista)
    {
        this.vista = vista;
        this.modelo = vista.getModelo();

        this.modelo.registrarObsevador(this);

        this.jugador1 = vista.getModelo().getJugador();
        this.jugador2 = vista.getModelo().getOponente();

        this.stackJugador1 = new StackPane();
        this.stackJugador2 = new StackPane();

        this.stackJugador1.setAlignment(Pos.CENTER_LEFT);
        this.stackJugador2.setAlignment(Pos.CENTER_RIGHT);

        // -------------------------------
        // Multimedia del botón.
        // -------------------------------
        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_draw.wav");
        this.audioClipCardDraw = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardDraw.setVolume(cardDrawVolume);

        // Inicialización.
        this.actualizarStackPaneDe(jugador1, stackJugador1);
        this.actualizarStackPaneDe(jugador1, stackJugador2);
    }

    private void actualizarStackPaneDe(Jugador jugador, StackPane stackPaneJugador)
    {

        Button botonMazo = new Button();

        Image imagenMazo = new Image(rutaImagenReversoCarta);

        botonMazo.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta, this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        botonMazo.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenMazo), CornerRadii.EMPTY, Insets.EMPTY)));

        int fontSizeInPoints = (int) Math.floor(this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente);

        Label label = new Label();
        label.setAlignment(Pos.CENTER);

        // Style.
        label.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta), this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        label.setTextFill(Color.web("#910101"));
        label.setFont(new Font("Bauhaus 93", fontSizeInPoints));

        label.setText(Integer.toString(this.vista.getModelo().getCantidadCartasRestantesMazoDe(jugador)));

        stackPaneJugador.getChildren().addAll(botonMazo, label);
    }

    public StackPane getPaneMazoJugador1()
    {

        return this.stackJugador1;
    }

    public StackPane getPaneMazoJugador2()
    {

        return this.stackJugador2;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void seTomoCartaDeMazo()
    {
        audioClipCardDraw.play();

        this.actualizarStackPaneDe(jugador1, stackJugador1);
        this.actualizarStackPaneDe(jugador2, stackJugador2);

        this.vista.actualizarDibujo();
    }

    @Override
    public void ingresoCartaAMano()
    {

    }

    @Override
    public void egresoCartaAMano()
    {

    }

    @Override
    public void ingresoCartaARegion()
    {

    }

    @Override
    public void egresoCartaARegion()
    {

    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {

    }

    @Override
    public void cartaCambioDeModo()
    {

    }
}

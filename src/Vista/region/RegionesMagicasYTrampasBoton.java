package Vista.region;

import Controlador.excepciones.NoSePuedeUsarMyTError;
import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Vista.Vista;
import Vista.visitador.VisitadorBotonRegionMagicasYTrampas;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;

import java.net.URL;

public class RegionesMagicasYTrampasBoton extends Button
{
    // Se uso como base una resolucion de 1920x1080 para los tamanos
    private static String backDeCartaLocacion = "resources/imagenes/cartaReverso.jpg";
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Carta carta;
    private Vista vista;
    private Popup popup;
    private Button botonCarta;
    private Jugador jugadorAsociado;
    private AudioClip audioClipCardActivation;
    private double cardActivationVolume = 0.3;
    private Button botonActivar;
    private Button botonCerrar;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMagicasYTrampasBoton(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;
        this.botonCarta = new Button();

        this.botonCarta.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);

        this.botonCarta.setStyle("-fx-border-width: 5px ; -fx-border-color: Black");
        String color = "0x11968e";
        double transparencia = 0.8;
        Background backgroundCarta = new Background(new BackgroundFill(Color.web(color, transparencia), CornerRadii.EMPTY, Insets.EMPTY));
        this.botonCarta.setBackground(backgroundCarta);

        // -------------------------------
        // Multimedia del botón.
        // -------------------------------
        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_activation.wav");
        this.audioClipCardActivation = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardActivation.setVolume(cardActivationVolume);
    }

    public Button getBoton()
    {

        return this.botonCarta;
    }

    public void actualizar(Carta carta)
    {
        this.carta = carta;
        this.botonCarta = this.crearBotonCarta();
    }

    private Button crearBotonCarta()
    {
        Button botonEnRegion = new Button();

        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        botonEnRegion.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        if (this.carta.estaBocaAbajo())
        {
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(backDeCartaLocacion).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        if (this.carta.estaBocaArriba())
        {
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        // -------------------------------

        // -------------------------------
        // Tooltip del botón.
        // -------------------------------
        // Solamente se muestran los tooltips de las cartas del jugador actual.
        if (this.vista.getControlador().getJugadorActual() == this.jugadorAsociado)
        {
            Image imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
            Tooltip tooltipBoton = new Tooltip();
            tooltipBoton.setGraphic(new ImageView(imagenBoton));
            botonEnRegion.setTooltip(tooltipBoton);
        }

        // Se crea el botón.
        this.popup = new Popup();
        popup.setAutoHide(true);
        VBox vbox = new VBox();

        this.botonActivar = new Button("Activar");
        // Las cartas trampa se activan solamente en la fase trampa, y de forma automática.
        this.carta.aceptar(new VisitadorBotonRegionMagicasYTrampas(this));

        this.botonCerrar = new Button("Cerrar");
        botonCerrar.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(botonActivar, botonCerrar);
        popup.getContent().addAll(vbox);

        if (this.vista.getControlador().getJugadorActual() == this.jugadorAsociado)
        {
            botonEnRegion.setOnAction(e -> magYTramEnRegionBtn_Click());
        }

        return botonEnRegion;
    }

    public void setBotonActivarCartaMagica()
    {
        botonActivar.setOnAction(e -> activarCartaMagicaDesdeRegionBtn_Click());
    }

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void magYTramEnRegionBtn_Click()
    {
        popup.show(vista.getPrimaryStage());
        Point2D point = this.botonCarta.localToScene(0.0, 0.0);
        popup.setX(vista.getPrimaryStage().getX() + point.getX());
        popup.setY(vista.getPrimaryStage().getY() + point.getY());
    }

    private void activarCartaMagicaDesdeRegionBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().activarCartaMagicaDesdeRegionMyT(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeUsarMyTError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }
        if (!thrown)
        {
            this.audioClipCardActivation.play();
        }
        popup.hide();
    }
}

package Vista.region;

import Controlador.excepciones.NoSePuedeAtacarError;
import Controlador.excepciones.NoSePuedeCambiarOrientacionError;
import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
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
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;

import java.net.URL;

public class RegionesMonstruoBoton extends Button
{
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    // Se uso como base una resolucion de 1920x1080 para los tamanos
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Tooltip tooltipBoton;
    private CartaMonstruo carta;
    private Button botonCarta;
    private Jugador jugadorAsociado;
    private Vista vista;
    private Image imagenBoton;
    private Button botonEnRegion;
    private Popup popup;
    private Popup popupDeSeleccion;
    private VBox vbox;
    private AudioClip audioClipCardFlip, audioClipCardAttack;
    private double cardFlipVolume = 0.7;
    private double cardAttackVolume = 0.3;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruoBoton(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;

        this.botonCarta = new Button();

        this.botonCarta.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);

        this.botonCarta.setStyle("-fx-border-width: 5px ; -fx-border-color: Black");
        String color = "0xff5733";
        double transparencia = 0.8;
        Background backgroundCarta = new Background(new BackgroundFill(Color.web(color,transparencia), CornerRadii.EMPTY, Insets.EMPTY));
        this.botonCarta.setBackground(backgroundCarta);

        // -------------------------------
        // Multimedia del botón.
        // -------------------------------
        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_flip.wav");
        this.audioClipCardFlip = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardFlip.setVolume(cardFlipVolume);

        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_attack.wav");
        this.audioClipCardAttack = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardAttack.setVolume(cardAttackVolume);
    }

    public Button getBoton()
    {
        return this.botonCarta;
    }

    public void actualizar(CartaMonstruo cartaMonstruo)
    {
        this.carta = cartaMonstruo;
        this.botonCarta = this.crearBotonCarta();
    }

    private Button crearBotonCarta()
    {
        this.botonEnRegion = new Button();
        this.tooltipBoton = new Tooltip();

        this.popup = new Popup();
        popup.setAutoHide(true);
        this.popupDeSeleccion = new Popup();
        popup.setAutoHide(true);
        this.vbox = new VBox();
        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        botonEnRegion.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);

        // Se la rota si está en defensa.
        if (this.carta.enDefensa())
        {
            this.botonEnRegion.getTransforms().add(new Rotate(90, (this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta) / 2,
                    (this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta) / 2));
        }

        // Se le pone el background.
        if (this.carta.estaBocaAbajo())
        {
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(rutaImagenReversoCarta).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        if (this.carta.estaBocaArriba())
        {
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        // -------------------------------
        // Tooltip del botón.
        // -------------------------------
        // Solamente se muestran los tooltips de las cartas del jugador actual.
        if (this.vista.getControlador().getJugadorActual() == this.jugadorAsociado)
        {
            this.imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
            tooltipBoton.setGraphic(new ImageView(imagenBoton));
            botonEnRegion.setTooltip(tooltipBoton);
        }

        // -------------------------------
        // Opciones del botón.
        // -------------------------------
        Button botonAtacar = new Button("Atacar");
        botonAtacar.setOnAction(e -> cartaMonstruoAtacarBtn_Click());

        Button botonCambiarModo = new Button("Cambiar modo");
        botonCambiarModo.setOnAction(e -> cambiarModoCartaMonstruoBtn_Click());

        Button botonDarVuelta = new Button("Dar Vuelta");
        botonDarVuelta.setOnAction(e -> flipCartaMonstruoBtn_Click());

        Button botonCerrar = new Button("Cerrar");
        botonCerrar.setOnAction(e -> popup.hide());

        // Se decide qué botón mostrar según el estado de la carta.
        if (this.carta.estaBocaArriba() && this.carta.enDefensa())
        {
            vbox.getChildren().addAll(botonCambiarModo, botonCerrar);
        } else if (this.carta.estaBocaArriba() && this.carta.enAtaque())
        {
            vbox.getChildren().addAll(botonAtacar, botonCambiarModo, botonCerrar);
        } else
        {
            vbox.getChildren().addAll(botonCambiarModo, botonDarVuelta, botonCerrar);
        }
        popup.getContent().addAll(vbox);

        // Solamente se muestran los tooltips de las cartas del jugador actual.
        if (this.vista.getControlador().getJugadorActual() == this.jugadorAsociado)
        {
            botonEnRegion.setOnAction(e -> monstruoEnRegionBtn_Click());
        }

        // -------------------------------

        return botonEnRegion;
    }

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void monstruoEnRegionBtn_Click()
    {
        this.popup.show(this.vista.getPrimaryStage());
        Point2D point = this.botonEnRegion.localToScene(0.0, 0.0);
        this.popup.setX(this.vista.getPrimaryStage().getX() + point.getX());
        this.popup.setY(this.vista.getPrimaryStage().getY() + point.getY());
    }

    private void cartaMonstruoAtacarBtn_Click()
    {

        Boolean thrown = false;

        try
        {
            this.vista.getControlador().atacar(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeAtacarError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardAttack.play();
        }
        popup.hide();
    }

    private void cambiarModoCartaMonstruoBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().cambiarModoCartaMonstruo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeCambiarOrientacionError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardFlip.play();
        }
        popup.hide();
    }

    private void flipCartaMonstruoBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().flipCarta(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeCambiarOrientacionError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardFlip.play();
        }
        popup.hide();
    }
}
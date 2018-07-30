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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ArrayList;

public class RegionesMonstruoBoton extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    private static String backDeCartaLocacion = "resources/imagenes/cartaReverso.jpg";
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
        this.botonCarta.setStyle(estiloRegion);

        this.botonEnRegion = new Button();
        this.tooltipBoton = new Tooltip();

        this.popup = new Popup();
        this.popupDeSeleccion = new Popup();
        this.vbox = new VBox();

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

    public void clear()
    {
        this.botonCarta = new Button();
        botonCarta.setStyle(estiloRegion);
        this.botonCarta.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
    }

    public void actualizar(CartaMonstruo cartaMonstruo)
    {
        this.carta = cartaMonstruo;
        this.botonCarta = this.crearBotonCarta();
    }

    private Button crearBotonCarta()
    {
        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        botonEnRegion.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        if (this.carta.enDefensa() && this.carta.estaBocaAbajo())
        {
            this.botonEnRegion.getTransforms().add(new Rotate(90, (this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta) / 2,
                    (this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta) / 2));
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(backDeCartaLocacion).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (this.carta.enDefensa())
        {
            this.botonEnRegion.getTransforms().add(new Rotate(90, (this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta) / 2,
                    (this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta) / 2));
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else
        {
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
            this.imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
        }

        // -------------------------------

        // -------------------------------
        // Tooltip del botón.
        // -------------------------------
        tooltipBoton.setGraphic(new ImageView(imagenBoton));
        botonEnRegion.setTooltip(tooltipBoton);
        // -------------------------------

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

        if (this.carta.estaBocaArriba() && this.carta.enDefensa())
        {
            vbox.getChildren().addAll(botonCambiarModo, botonCerrar);
        } 
        else if(this.carta.estaBocaArriba() && this.carta.enAtaque()){
        	vbox.getChildren().addAll(botonAtacar, botonCambiarModo, botonCerrar);
        }
        else
        {
            vbox.getChildren().addAll(botonCambiarModo, botonDarVuelta, botonCerrar);
        }
        popup.getContent().addAll(vbox);

        botonEnRegion.setOnAction(e -> monstruoEnRegionBtn_Click(popup, botonEnRegion));
        // -------------------------------

        return botonEnRegion;
    }

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void monstruoEnRegionBtn_Click(Popup popup, Button botonEnRegion)
    {
        popup.show(this.vista.getPrimaryStage());
        Point2D point = botonEnRegion.localToScene(0.0, 0.0);
        popup.setX(this.vista.getPrimaryStage().getX() + point.getX());
        popup.setY(this.vista.getPrimaryStage().getY() + point.getY());
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
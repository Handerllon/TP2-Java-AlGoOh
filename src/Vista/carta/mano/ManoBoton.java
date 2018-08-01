package Vista.carta.mano;

import Controlador.excepciones.NoSePuedeEnviarARegionCampoError;
import Controlador.excepciones.NoSePuedeEnviarCartaMonstruoARegionError;
import Controlador.excepciones.NoSePuedeEnviarMyTARegionError;
import Controlador.excepciones.NoSePuedeUsarMyTError;
import Modelo.Jugador;
import Modelo.carta.Carta;
import Vista.Vista;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.URL;

public class ManoBoton extends Button
{
    // Se uso como base una resolucion de 1080x1920
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private static String locacionDeBackDeCarta = "resources/imagenes/cartaReverso.jpg";
    private Stage primaryStage;
    private Carta carta;
    private Button boton;
    private Vista vista;
    private Jugador jugadorAsociado;
    private Tooltip toolTip;
    private Popup popup;
    private VBox vbox;
    private Image imagenCarta;
    private AudioClip audioClipCardMove, audioClipCardActivation;
    private double cardActivationVolume = 0.3;
    private double cardMoveVolume = 0.3;

    public ManoBoton(Vista vista, Carta carta, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.carta = carta;
        this.vista = vista;
        this.primaryStage = vista.getPrimaryStage();

        this.boton = new Button();
        this.toolTip = new Tooltip();
        this.popup = new Popup();
        popup.setAutoHide(true);
        this.vbox = new VBox();
        this.boton.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);

        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        if (this.vista.getControlador().getJugadorActual() == this.jugadorAsociado)
        {
            this.imagenCarta = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
            this.toolTip.setGraphic(new ImageView(imagenCarta));
            this.boton.setTooltip(toolTip);
            this.boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else
        {
            this.boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(locacionDeBackDeCarta).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        // -------------------------------
        // Multimedia del botón.
        // -------------------------------
        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_move.wav");
        this.audioClipCardMove = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardMove.setVolume(cardMoveVolume);

        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_activation.wav");
        this.audioClipCardActivation = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardActivation.setVolume(cardActivationVolume);

        // Se decide qué tipo de botón crear.
        if (carta.esMonstruo())
        {
            this.crearBotonMonstruo();
        } else if (carta.esMagica())
        {
            this.crearBotonCartaMagica();
        } else if (carta.esTrampa())
        {
            this.crearBotonCartaTrampa();
        } else if (carta.esCampo())
        {
            this.crearBotonCartaCampo();
        }

        popup.getContent().addAll(vbox);

        if (this.vista.getControlador().getJugadorActual() == this.jugadorAsociado)
        {
            boton.setOnAction(e -> accionBtn_Click(popup, boton));
        }
    }

    private void accionBtn_Click(Popup popup, Button boton)
    {
        popup.show(primaryStage);
        Point2D point = boton.localToScene(0.0, 0.0);
        popup.setX(primaryStage.getX() + point.getX());
        popup.setY(primaryStage.getY() + point.getY());

    }

    public Button getBoton()
    {
        return this.boton;
    }

    // --------------------------------------------------------------------
    // Botón Carta Monstruo.
    // --------------------------------------------------------------------
    private void crearBotonMonstruo()
    {
        // Se crea la VBox.
        Button b1 = new Button("Invocar");
        b1.setOnAction(e -> summonCartaMonstruoBtn_Click());

        Button b2 = new Button("Posicionar");
        b2.setOnAction(e -> setCartaMonstruoBtn_Click());

        Button b3 = new Button("Cerrar");
        b3.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2, b3);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void summonCartaMonstruoBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().summonCartaMonstruo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarCartaMonstruoARegionError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardMove.play();
        }
        popup.hide();
    }

    private void setCartaMonstruoBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().setCartaMonstruo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarCartaMonstruoARegionError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardMove.play();
        }
        popup.hide();
    }

    // --------------------------------------------------------------------
    // Botón Carta Mágica.
    // --------------------------------------------------------------------
    private void crearBotonCartaMagica()
    {
        // Se crea la VBox.
        Button b1 = new Button("Activar");
        b1.setOnAction(e -> activarCartaMagicaDesdeManoBtn_Click());
        Button b2 = new Button("Posicionar");
        b2.setOnAction(e -> setCartaMagicaBtn_Click());

        Button b3 = new Button("Cerrar");
        b3.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2, b3);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void setCartaMagicaBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().setCartaMagica(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarMyTARegionError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardMove.play();
        }
        popup.hide();
    }

    private void activarCartaMagicaDesdeManoBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().activarCartaMagicaDesdeMano(this.jugadorAsociado, this.carta);
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

    // --------------------------------------------------------------------
    // Botón Carta Trampa.
    // --------------------------------------------------------------------
    private void crearBotonCartaTrampa()
    {
        Button b1 = new Button("Posicionar");
        b1.setOnAction(e -> setCartaTrampaBtn_Click());

        Button b2 = new Button("Cerrar");
        b2.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void setCartaTrampaBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().setCartaTrampa(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarMyTARegionError error)
        {
            thrown = true;
            this.vista.mostrarError(error);
        }

        if (!thrown)
        {
            this.audioClipCardMove.play();
        }
        popup.hide();
    }

    // --------------------------------------------------------------------
    // Botón Carta Campo.
    // --------------------------------------------------------------------
    private void crearBotonCartaCampo()
    {
        // Se crea la VBox.
        Button b1 = new Button("Activar");
        b1.setOnAction(e -> activarCartaCampoBtn_Click());

        Button b2 = new Button("Cerrar");
        b2.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void activarCartaCampoBtn_Click()
    {
        Boolean thrown = false;
        try
        {
            this.vista.getControlador().activarCartaCampoDesdeMano(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarARegionCampoError error)
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

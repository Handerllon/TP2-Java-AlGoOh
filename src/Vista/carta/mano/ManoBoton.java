package Vista.carta.mano;

import Controlador.excepciones.NoSePuedeEnviarARegionCampoError;
import Controlador.excepciones.NoSePuedeEnviarCartaMonstruoARegionError;
import Controlador.excepciones.NoSePuedeEnviarMyTARegionError;
import Controlador.excepciones.NoSePuedeUsarMyTError;
import Modelo.Jugador;
import Modelo.carta.Carta;
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
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ManoBoton extends Button
{
    // Se uso como base una resolucion de 1080x1920
    private static double anchoInicialCarta = 95.4;
    private static double altoInicialCarta = 139;
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private Stage primaryStage;
    private Carta carta;
    private Button boton;
    private Vista vista;
    private Jugador jugadorAsociado;
    private Tooltip toolTip;
    private Popup popup;
    private VBox vbox;
    private Image imagenCarta;

    public ManoBoton(Vista vista, Carta carta, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.carta = carta;
        this.vista = vista;
        this.primaryStage = vista.getPrimaryStage();

        this.imagenCarta = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
        this.boton = new Button();
        this.toolTip = new Tooltip();
        this.popup = new Popup();
        this.vbox = new VBox();

        this.toolTip.setGraphic(new ImageView(imagenCarta));
        this.boton.setTooltip(toolTip);
        double anchoCarta = (this.vista.getResolucionHorizontal() * anchoInicialCarta) / 1920;
        double altoCarta = (this.vista.getResolucionVertical() * altoInicialCarta) / 1080;
        this.boton.setPrefSize(anchoCarta, altoCarta);

        this.boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

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
        boton.setOnAction(e -> accionBtn_Click(popup, boton));
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

        // TODO: no debería ser un closeRequest?
        Button b3 = new Button("Cerrar");
        b3.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2, b3);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void summonCartaMonstruoBtn_Click()
    {
        try
        {
            this.vista.getControlador().summonCartaMonstruo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarCartaMonstruoARegionError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }

    private void setCartaMonstruoBtn_Click()
    {
        try
        {
            this.vista.getControlador().setCartaMonstruo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarCartaMonstruoARegionError error)
        {
            this.vista.mostrarError(error);
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
        b1.setOnAction(e -> activarCartaMagicaBtn_Click());
        Button b2 = new Button("Posicionar");
        b2.setOnAction(e -> setCartaMagicaBtn_Click());

        // TODO: no debería ser un closeRequest?
        Button b3 = new Button("Cerrar");
        b3.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2, b3);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void setCartaMagicaBtn_Click()
    {
        try
        {
            this.vista.getControlador().setCartaMagica(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarMyTARegionError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }

    private void activarCartaMagicaBtn_Click()
    {
        try
        {
            // TODO: hay que implementar que se pueda activar directamente desde la mano.
            this.vista.getControlador().activarCartaMagica(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeUsarMyTError error)
        {
            this.vista.mostrarError(error);
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

        // TODO: no debería ser un closeRequest?
        Button b2 = new Button("Cerrar");
        b2.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void setCartaTrampaBtn_Click()
    {
        try
        {
            this.vista.getControlador().setCartaTrampa(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarMyTARegionError error)
        {
            this.vista.mostrarError(error);
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

        // TODO: no debería ser un closeRequest?
        Button b2 = new Button("Cerrar");
        b2.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);
    }

    // -------------------------------
    // Implementación de botones.
    // -------------------------------
    private void activarCartaCampoBtn_Click()
    {
        try
        {
            this.vista.getControlador().activarCartaCampo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeEnviarARegionCampoError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }
}

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;

public class RegionesMonstruoBoton extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    private static String backDeCartaLocacion = "resources/imagenes/cartaReverso.jpg";
    // Se uso como base una resolucion de 1920x1080 para los tamanos
    private static double anchoInicialCarta = 95.4;
    private static double altoInicialCarta = 139;
    private Tooltip tooltipBoton;
    private CartaMonstruo carta;
    private Button botonCarta;
    private Jugador jugadorAsociado;
    private Vista vista;
    private double anchoDeCarta;
    private double altoDeCarta;
    private Image imagenBoton;
    private Button botonEnRegion;
    private Popup popup;
    private VBox vbox;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruoBoton(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;

        this.botonCarta = new Button();

        this.anchoDeCarta = (this.vista.getResolucionHorizontal() * anchoInicialCarta) / 1920;
        this.altoDeCarta = (this.vista.getResolucionVertical() * altoInicialCarta) / 1080;
        this.botonCarta.setPrefSize(anchoDeCarta, altoDeCarta);
        this.botonCarta.setStyle(estiloRegion);

        this.botonEnRegion = new Button();
        this.tooltipBoton = new Tooltip();

        this.popup = new Popup();
        this.vbox = new VBox();
    }

    public Button getBoton()
    {
        return this.botonCarta;
    }

    public void clear()
    {
        this.botonCarta = new Button();
        botonCarta.setStyle(estiloRegion);
        this.botonCarta.setPrefSize(anchoDeCarta, altoDeCarta);
    }

    public void actualizar(CartaMonstruo cartaMonstruo)
    {
        // TODO: para que crear de nuevo el botón? por qué no lo actualiza con los datos nuevos?
        this.carta = cartaMonstruo;
        this.botonCarta = this.crearBotonCarta();
    }

    private Button crearBotonCarta()
    {
        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        botonEnRegion.setPrefSize(anchoDeCarta, altoDeCarta);
        if (this.carta.enDefensa() && this.carta.estaBocaAbajo())
        {
            this.botonEnRegion.getTransforms().add(new Rotate(90, anchoDeCarta / 2, altoDeCarta / 2));
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(backDeCartaLocacion).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (this.carta.enDefensa())
        {
            this.botonEnRegion.getTransforms().add(new Rotate(90, anchoDeCarta / 2, altoDeCarta / 2));
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
        Button b1 = new Button("Atacar");
        b1.setOnAction(e -> cartaMonstruoAtacarBtn_Click());

        Button b2 = new Button("Cambiar modo");
        b2.setOnAction(e -> cambiarModoCartaMonstruoBtn_Click());

        Button b3 = new Button("Dar Vuelta");
        b3.setOnAction(e -> flipCartaMonstruoBtn_Click());

        // TODO: no debería ser un closeRequest?
        Button b4 = new Button("Cerrar");
        b4.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2, b3, b4);
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
        try
        {
            this.vista.getControlador().atacar(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeAtacarError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }

    private void cambiarModoCartaMonstruoBtn_Click()
    {
        try
        {
            this.vista.getControlador().cambiarModoCartaMonstruo(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeCambiarOrientacionError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }

    private void flipCartaMonstruoBtn_Click()
    {
        try
        {
            this.vista.getControlador().flipCarta(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeCambiarOrientacionError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }
}
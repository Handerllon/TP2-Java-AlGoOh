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
import javafx.stage.Popup;

public class RegionesMonstruoBoton extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    // Se uso como base una resolucion de 1920x1080 para los tamanos
    private static double anchoInicialCarta = 95.4;
    private static double altoInicialCarta = 139;
    private CartaMonstruo carta;
    private Button botonCarta;
    private Jugador jugadorAsociado;
    private Vista vista;
	private double anchoDeCarta;
	private double altoDeCarta;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruoBoton(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;

        this.botonCarta = new Button();
        
        this.anchoDeCarta = (this.vista.getResolucionHorizontal()*anchoInicialCarta)/1920;
        this.altoDeCarta = (this.vista.getResolucionVertical()*altoInicialCarta)/1080;
        this.botonCarta.setPrefSize(anchoDeCarta, altoDeCarta);
        this.botonCarta.setStyle(estiloRegion);
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
        Button botonEnRegion = new Button();

        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        Image imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
        botonEnRegion.setPrefSize(anchoDeCarta, altoDeCarta);
        botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                .getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        // -------------------------------

        // -------------------------------
        // Tooltip del botón.
        // -------------------------------
        Tooltip tooltipBoton = new Tooltip();
        tooltipBoton.setGraphic(new ImageView(imagenBoton));
        botonEnRegion.setTooltip(tooltipBoton);
        // -------------------------------

        // -------------------------------
        // Opciones del botón.
        // -------------------------------
        Popup popup = new Popup();
        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMonstruo(vbox, popup);
        popup.getContent().addAll(vbox);

        botonEnRegion.setOnAction(e -> monstruoEnRegionBtn_Click(popup, botonEnRegion));
        // -------------------------------

        return botonEnRegion;
    }

    private VBox crearVBoxCartaMonstruo(VBox vbox, Popup popup)
    {
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

        return vbox;
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
            this.vista.getControlador().atacar(this.carta, this.jugadorAsociado);
        } catch (NoSePuedeAtacarError error)
        {
            this.vista.mostrarError(error);
        }
    }

    private void cambiarModoCartaMonstruoBtn_Click()
    {
        try
        {
            this.vista.getControlador().cambiarModoCartaMonstruo(this.carta, this.jugadorAsociado);
        } catch (NoSePuedeCambiarOrientacionError error)
        {
            this.vista.mostrarError(error);
        }
    }

    private void flipCartaMonstruoBtn_Click()
    {
        try
        {
            this.vista.getControlador().flipCarta(this.carta, this.jugadorAsociado);
        } catch (NoSePuedeCambiarOrientacionError error)
        {
            this.vista.mostrarError(error);
        }
    }
}
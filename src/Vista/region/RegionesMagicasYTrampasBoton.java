package Vista.region;

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

public class RegionesMagicasYTrampasBoton extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    // Se uso como base una resolucion de 1920x1080 para los tamanos
    private static String backDeCartaLocacion = "resources/imagenes/cartaReverso.jpg";
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Carta carta;
    private Vista vista;
    private Popup popup;
    private Button botonDeLaCarta;
    private Jugador jugadorAsociado;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMagicasYTrampasBoton(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;
        this.botonDeLaCarta = new Button();

        this.botonDeLaCarta.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta,
        		this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
        this.botonDeLaCarta.setStyle(estiloRegion);
    }

    public Button getBoton()
    {

        return this.botonDeLaCarta;
    }

    public void clear()
    {
        this.botonDeLaCarta = new Button();
        botonDeLaCarta.setStyle(estiloRegion);
        this.botonDeLaCarta.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta,
        		this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
    }

    public void actualizar(Carta carta)
    {
        this.carta = carta;
        this.botonDeLaCarta = this.crearBotonCarta();
    }

    private Button crearBotonCarta()
    {
        Button botonEnRegion = new Button();

        // -------------------------------
        // Imagen del botón.
        // -------------------------------
        Image imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
        botonEnRegion.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta,
        		this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
        if (this.carta.estaBocaAbajo())
        {
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(backDeCartaLocacion).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else
        {
            Tooltip tooltipBoton = new Tooltip();
            tooltipBoton.setGraphic(new ImageView(imagenBoton));
            botonEnRegion.setTooltip(tooltipBoton);
            botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        // -------------------------------

        // -------------------------------
        // Tooltip del botón.
        // -------------------------------
        // -------------------------------

        // Se crea el botón.
        this.popup = new Popup();
        VBox vbox = new VBox();

        Button b1 = new Button("Activar");
        if (this.carta.esMagica())
        {
            b1.setOnAction(e -> activarCartaMagicaBtn_Click());
        } else if (this.carta.esTrampa())
        {
            b1.setOnAction(e -> activarCartaTrampaBtn_Click());
        }

        Button b2 = new Button("Cerrar");
        b2.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);
        popup.getContent().addAll(vbox);

        botonEnRegion.setOnAction(e -> magYTramEnRegionBtn_Click());

        return botonEnRegion;
    }

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void magYTramEnRegionBtn_Click()
    {
        popup.show(vista.getPrimaryStage());
        Point2D point = this.botonDeLaCarta.localToScene(0.0, 0.0);
        popup.setX(vista.getPrimaryStage().getX() + point.getX());
        popup.setY(vista.getPrimaryStage().getY() + point.getY());
    }

    private void activarCartaMagicaBtn_Click()
    {
        try
        {
            this.vista.getControlador().activarCartaMagica(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeUsarMyTError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }

    private void activarCartaTrampaBtn_Click()
    {
        try
        {
            this.vista.getControlador().activarCartaTrampa(this.jugadorAsociado, this.carta);
        } catch (NoSePuedeUsarMyTError error)
        {
            this.vista.mostrarError(error);
        }
        popup.hide();
    }
}

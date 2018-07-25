package Vista.Botones;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Vista.Vista;
import javafx.geometry.Insets;
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

public class BotonMagicasYTrampasEnRegion extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Carta carta;
    private Vista vista;
    private Popup popup;
    private Button botonDeLaCarta;
    private Jugador jugadorAsociado;

    public BotonMagicasYTrampasEnRegion(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;
        this.botonDeLaCarta = new Button();

        // TODO: generalizar el hardcodeo de los numeros.
        this.botonDeLaCarta.setPrefSize(anchoDeCarta, altoDeCarta);
        this.botonDeLaCarta.setStyle(estiloRegion);
    }

    public Button obtenerBoton()
    {

        return this.botonDeLaCarta;
    }

    public void clear()
    {
        this.botonDeLaCarta = new Button();
        botonDeLaCarta.setStyle(estiloRegion);
        this.botonDeLaCarta.setPrefSize(anchoDeCarta, altoDeCarta);
    }

    public void actualizar(Carta carta)
    {

        this.carta = carta;
        this.botonDeLaCarta = this.crearBotonParaCartaEnRegion();
    }

    private Button crearBotonParaCartaEnRegion()
    {
        Button botonEnRegion = new Button();

        //-----Tooltip del boton------
        Image image = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        botonEnRegion.setTooltip(tp);
        //----------------------------

        //----- Fondo del boton--------
        botonEnRegion.setPrefSize(anchoDeCarta, altoDeCarta);
        botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        //------------------------------

        if (this.carta.getClass() == CartaMagica.class)
        {

            this.crearBotonParaCartaMagicaEnRegion(botonEnRegion);
        }

        return botonEnRegion;
    }

    private void crearBotonParaCartaMagicaEnRegion(Button botonEnRegion)
    {

        this.popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMagica(vbox, popup);

        popup.getContent().addAll(vbox);

        botonEnRegion.setOnAction(e -> magYTramEnRegionBtn_Click());
    }

    private VBox crearVBoxCartaMagica(VBox vbox, Popup popup)
    {
        //TODO: Implementar los event handler de cada opcion
        Button b1 = new Button("Usar");
        Button b5 = new Button("Cerrar");

        b1.setOnAction(e -> magYTramEnRegionUsarBtn_Click());

        b5.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b5);

        return vbox;
    }

    private void magYTramEnRegionUsarBtn_Click()
    {
        this.vista.getControlador().activarCartaMagica(this.carta, this.jugadorAsociado);
    }

    private void magYTramEnRegionBtn_Click()
    {

        popup.show(vista.getPrimaryStage());
        javafx.geometry.Point2D point = this.botonDeLaCarta.localToScene(0.0, 0.0);
        popup.setX(vista.getPrimaryStage().getX() + point.getX());
        popup.setY(vista.getPrimaryStage().getY() + point.getY());
    }
}

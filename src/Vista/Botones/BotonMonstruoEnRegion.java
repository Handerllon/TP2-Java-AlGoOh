package Vista.Botones;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
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

import java.util.ArrayList;

public class BotonMonstruoEnRegion extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    // TODO: número mágico.
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private CartaMonstruo carta;
    private Button botonDeLaCarta;
    private Jugador jugadorAsociado;
    private Vista vista;

    public BotonMonstruoEnRegion(Vista vista, Jugador jugador)
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

    public void actualizar(CartaMonstruo cartaMonstruo)
    {

        this.carta = cartaMonstruo;
        this.botonDeLaCarta = this.crearBotonParaCartaEnRegion();
    }

    private Button crearBotonParaCartaEnRegion()
    {
        Button botonEnRegion = new Button();

        //-----Tooltip del boton------
        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        botonEnRegion.setTooltip(tp);
        //----------------------------

        //----- Fondo del boton--------
        botonEnRegion.setPrefSize(anchoDeCarta, altoDeCarta);
        botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        //------------------------------

        //----Opciones Del Boton -------

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMonstruo(vbox, popup);

        popup.getContent().addAll(vbox);

        botonEnRegion.setOnAction(e -> MonstruoEnRegionBtn_Click(popup, botonEnRegion));
        //------------------------------

        return botonEnRegion;
    }

    private void MonstruoEnRegionBtn_Click(Popup popup, Button botonEnRegion)
    {
        popup.show(this.vista.getPrimaryStage());
        javafx.geometry.Point2D point = botonEnRegion.localToScene(0.0, 0.0);
        popup.setX(this.vista.getPrimaryStage().getX() + point.getX());
        popup.setY(this.vista.getPrimaryStage().getY() + point.getY());
    }

    private VBox crearVBoxCartaMonstruo(VBox vbox, Popup popup)
    {

        //TODO: Implementar los event handler de cada opcion
        Button b2 = new Button("Atacar");
        Button b3 = new Button("Cambiar Orientacion");
        Button b4 = new Button("Dar Vuelta");
        Button b5 = new Button("Cerrar");

        b2.setOnAction(e -> cartaMonstruoAtacarBtn_Click());

        b3.setOnAction(e -> cartaMonstruoCambiarModoBtn_Click());

        b4.setOnAction(e -> flipCartaMonstruoBtn_Click());

        b5.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b2, b3, b4, b5);

        return vbox;
    }

    private void cartaMonstruoAtacarBtn_Click()
    {

        ArrayList<CartaMonstruo> cartasOponente = this.vista.getModelo().getCartasEnRegionMonstruosOponente();

        Button b0 = new Button("1");
        Button b1 = new Button("2");
        Button b2 = new Button("3");
        Button b3 = new Button("4");
        Button b4 = new Button("5");
        Button b5 = new Button("Atacar directamente al oponente");
        Button b6 = new Button("Cerrar");

        Popup popup = new Popup();
        popup.getContent().addAll(b0, b1, b2, b3, b4, b5, b6);

        b0.setOnAction(e -> cartaMonstruoAtacarMonstruoBtn_Click(0, cartasOponente));
        b1.setOnAction(e -> cartaMonstruoAtacarMonstruoBtn_Click(1, cartasOponente));
        b2.setOnAction(e -> cartaMonstruoAtacarMonstruoBtn_Click(2, cartasOponente));
        b3.setOnAction(e -> cartaMonstruoAtacarMonstruoBtn_Click(3, cartasOponente));
        b4.setOnAction(e -> cartaMonstruoAtacarMonstruoBtn_Click(4, cartasOponente));
        b5.setOnAction(e -> cartaMonstruoAtacarOponenteBtn_Click());
        b6.setOnAction(e -> popup.hide());
    }

    private void cartaMonstruoAtacarOponenteBtn_Click()
    {

        this.vista.getControlador().atacar(this.carta, this.jugadorAsociado);
    }

    private void cartaMonstruoAtacarMonstruoBtn_Click(int i, ArrayList<CartaMonstruo> cartasOponente)
    {

        // TODO: ojo con esta condición, no estoy seguro que un array devuelva null si no está el i-ésimo elemento.
        if (cartasOponente.get(i) != null)
        {
            this.vista.getControlador().atacar(this.carta, cartasOponente.get(i), this.jugadorAsociado);
        }
    }

    private void cartaMonstruoCambiarModoBtn_Click()
    {
        this.vista.getControlador().cambiarModoCartaMonstruo(this.carta, null);
    }

    private void flipCartaMonstruoBtn_Click()
    {
        this.vista.getControlador().flipCartaMonstruo(this.carta, null);
    }
}

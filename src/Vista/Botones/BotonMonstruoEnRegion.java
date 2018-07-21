package Vista.Botones;

import Controlador.excepciones.JugadorNoPermitidoParaJugar;
import Controlador.excepciones.NoEsUnaCartaParaAtacar;
import Modelo.carta.CartaNula;
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
    //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private CartaMonstruo carta;
    private Button botonDeLaCarta;
    private Vista vista;

    public BotonMonstruoEnRegion(Vista vista)
    {
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
                .getResource(this.carta.obtenerLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        botonEnRegion.setTooltip(tp);
        //----------------------------

        //----- Fondo del boton--------
        botonEnRegion.setPrefSize(anchoDeCarta, altoDeCarta);
        botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
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
        popup.show(this.vista.obtenerPrimaryStage());
        javafx.geometry.Point2D point = botonEnRegion.localToScene(0.0, 0.0);
        popup.setX(this.vista.obtenerPrimaryStage().getX() + point.getX());
        popup.setY(this.vista.obtenerPrimaryStage().getY() + point.getY());
    }

    private VBox crearVBoxCartaMonstruo(VBox vbox, Popup popup)
    {

        //TODO: Implementar los event handler de cada opcion
        Button b2 = new Button("Atacar");
        Button b3 = new Button("Cambiar Orientacion");
        Button b4 = new Button("Dar Vuelta");
        Button b5 = new Button("Cerrar");

        b2.setOnAction(e -> cartaMonstruoAtacarBtn_Click());

        b3.setOnAction(e -> cartaMonstruoCambiarOrientacionBtn_Click());

        b4.setOnAction(e -> cartaMonstruoDarVueltaBtn_Click());

        b5.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b2, b3, b4, b5);

        return vbox;
    }

    private void cartaMonstruoAtacarBtn_Click()
    {

        ArrayList<CartaMonstruo> cartasOponente = this.vista.obtenerModelo().obtenerCartasEnRegionMonstruosOponente();

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
        try
        {
            this.vista.obtenerControlador().atacar(this.carta, null);
        } catch (JugadorNoPermitidoParaJugar jugadorNoPermitidoParaJugar)
        {
            jugadorNoPermitidoParaJugar.printStackTrace();
        }
    }

    private void cartaMonstruoAtacarMonstruoBtn_Click(int i, ArrayList<CartaMonstruo> cartasOponente)
    {

        // TODO: ojo con esta condición, no estoy seguro que un array devuelva null si no está el i-ésimo elemento.
        if (cartasOponente.get(i) == null)
        {
            try
            {
                this.vista.obtenerControlador().atacar(this.carta, CartaNula.obtenerInstancia(), null);
            } catch (NoEsUnaCartaParaAtacar noEsUnaCartaParaAtacar)
            {
                noEsUnaCartaParaAtacar.printStackTrace();
            } catch (JugadorNoPermitidoParaJugar jugadorNoPermitidoParaJugar)
            {
                jugadorNoPermitidoParaJugar.printStackTrace();
            }
        } else
        {
            try
            {
                this.vista.obtenerControlador().atacar(this.carta, cartasOponente.get(i), null);
            } catch (JugadorNoPermitidoParaJugar jugadorNoPermitidoParaJugar)
            {
                jugadorNoPermitidoParaJugar.printStackTrace();
            }
        }
    }

    private void cartaMonstruoCambiarOrientacionBtn_Click()
    {

        try
        {
            this.vista.obtenerControlador().cambiarModoCartaMonstruo(this.carta, null);
        } catch (JugadorNoPermitidoParaJugar jugadorNoPermitidoParaJugar)
        {
            jugadorNoPermitidoParaJugar.printStackTrace();
        }
    }

    private void cartaMonstruoDarVueltaBtn_Click()
    {
        try
        {
            this.vista.obtenerControlador().voltearCartaMonstruo(this.carta, null);
        } catch (JugadorNoPermitidoParaJugar jugadorNoPermitidoParaJugar)
        {
            jugadorNoPermitidoParaJugar.printStackTrace();
        }
    }
}

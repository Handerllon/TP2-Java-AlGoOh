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

        this.botonDeLaCarta.setPrefSize(anchoDeCarta, altoDeCarta);
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
        this.botonDeLaCarta.setPrefSize(anchoDeCarta, altoDeCarta);
    }

    public void actualizar(CartaMonstruo cartaMonstruo)
    {
        // TODO: para que crear de nuevo el botón? por qué no lo actualiza con los datos nuevos?
        this.carta = cartaMonstruo;
        this.botonDeLaCarta = this.crearBotonParaCartaEnRegion();
    }

    private Button crearBotonParaCartaEnRegion()
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

        botonEnRegion.setOnAction(e -> MonstruoEnRegionBtn_Click(popup, botonEnRegion));
        // -------------------------------

        return botonEnRegion;
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

    private void MonstruoEnRegionBtn_Click(Popup popup, Button botonEnRegion)
    {
        popup.show(this.vista.getPrimaryStage());
        javafx.geometry.Point2D point = botonEnRegion.localToScene(0.0, 0.0);
        popup.setX(this.vista.getPrimaryStage().getX() + point.getX());
        popup.setY(this.vista.getPrimaryStage().getY() + point.getY());
    }

    // TODO: no me parece buena esta solucion ya que la vista va a tener que verificar si el jugador puede atacar a
    // las cartas del oponente o al oponente directamente. O sea, no se le puede dar la opcion al jugador de atacar
    // directamene al oponente si hay cartas en la región monstruo oponente para atacar. Lo que creo que debería
    // suceder es que el jugador toca la carta monstruo para efectuar un ataque, este comando se le pasa al
    // controlador, y luego el controlador realiza los chequeos que deba realizar. Si se habilita el ataque, van a
    // suceder dos cosas en el controlador:
    // 1. si no hay cartas en la región monstruo del oponente, se ataca directamente al oponente.
    // 2. si hay cartas en la región monstruo del oponente, el controlador le va a pedir a la vista que le muestre al
    // jugador un menú con las cartas que puede atacar (similar a cuando se le piden los sacrificios).
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
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        // Ojo con tratar de atacar varias veces en el mismo turno.
        // Hay un solo metodo atacar() en ControladorInterfaz.
        //this.vista.getControlador().atacar(this.carta, this.jugadorAsociado);
    }

    private void cartaMonstruoAtacarMonstruoBtn_Click(int i, ArrayList<CartaMonstruo> cartasOponente)
    {

        // TODO: ojo con esta condición, no estoy seguro que un array devuelva null si no está el i-ésimo elemento.
        if (cartasOponente.get(i) != null)
        {
            // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
            // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
            // Ojo con tratar de atacar varias veces en el mismo turno.
            // Hay un solo metodo atacar() en ControladorInterfaz.
            //this.vista.getControlador().atacar(this.carta, cartasOponente.get(i), this.jugadorAsociado);
        }
    }

    private void cartaMonstruoCambiarModoBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        // Ojo con tratar de cambiar modo varias veces en el mismo turno.
        this.vista.getControlador().cambiarModoCartaMonstruo(this.carta, null);
    }

    private void flipCartaMonstruoBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        // Ojo con tratar de dar vueltas varias veces en el mismo turno.
        this.vista.getControlador().flipCarta(this.carta, null);
    }
}

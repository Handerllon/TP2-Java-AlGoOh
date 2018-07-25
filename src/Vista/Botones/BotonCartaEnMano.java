package Vista.Botones;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class BotonCartaEnMano extends Button
{
    // TODO: número mágico.
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    Stage primaryStage;
    private Carta carta;
    private Button botonDeLaCarta;
    private Vista vista;
    private Jugador jugadorAsociado;

    public BotonCartaEnMano(Vista vista, Carta carta, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.carta = carta;
        this.vista = vista;
        this.primaryStage = vista.getPrimaryStage();

        if (carta.getClass() == CartaMonstruo.class)
        {
            this.botonDeLaCarta = this.crearBotonMonstruoEnMano();
        } else if (carta.getClass() == CartaMagica.class)
        {
            this.botonDeLaCarta = this.crearBotonCartaMagicaEnMano();
        } else
            this.botonDeLaCarta = this.crearBotonCartaTrampaEnMano();
    }

    public Button getBoton()
    {

        return this.botonDeLaCarta;
    }

    private Button crearBotonCartaTrampaEnMano()
    {

        Button boton = new Button();

        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        boton.setPrefSize(anchoDeCarta, altoDeCarta);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaTrampaEnMano(vbox, popup);

        popup.getContent().addAll(vbox);

        boton.setOnAction(e -> accionBtn_Click(popup, boton));

        return boton;
    }

    private VBox crearVBoxCartaTrampaEnMano(VBox vbox, Popup popup)
    {
        Button b1 = new Button("Jugar");
        Button b2 = new Button("Cerrar");

        b1.setOnAction(e -> setCartaTrampaBtn_Click());

        b2.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);

        return vbox;
    }

    private Button crearBotonCartaMagicaEnMano()
    {
        Button boton = new Button();

        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        boton.setPrefSize(anchoDeCarta, altoDeCarta);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMagicaEnMano(vbox, popup);

        popup.getContent().addAll(vbox);

        boton.setOnAction(e -> accionBtn_Click(popup, boton));

        return boton;
    }

    private VBox crearVBoxCartaMagicaEnMano(VBox vbox, Popup popup)
    {
        Button b1 = new Button("Jugar Boca Arriba");
        Button b2 = new Button("Jugar Boca Abajo");
        Button b3 = new Button("Cerrar");

        b1.setOnAction(e -> activarCartaMagicaBtn_Click());

        b2.setOnAction(e -> setCartaMagicaBtn_Click());

        b3.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2, b3);

        return vbox;
    }

    private Button crearBotonMonstruoEnMano()
    {
        Button boton = new Button();

        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        boton.setPrefSize(anchoDeCarta, altoDeCarta);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMonstruoEnMano(vbox, popup, primaryStage);

        popup.getContent().addAll(vbox);

        boton.setOnAction(e -> accionBtn_Click(popup, boton));

        return boton;
    }

    private void accionBtn_Click(Popup popup, Button boton)
    {

        popup.show(primaryStage);
        javafx.geometry.Point2D point = boton.localToScene(0.0, 0.0);
        popup.setX(primaryStage.getX() + point.getX());
        popup.setY(primaryStage.getY() + point.getY());
    }

    private VBox crearVBoxCartaMonstruoEnMano(VBox vbox, Popup popup, Stage primaryStage)
    {
        Button b1 = new Button("Jugar");
        Button b2 = new Button("Orientar En Defensa");
        Button b3 = new Button("Orientar En Ataque");
        Button b4 = new Button("Boca arriba");
        Button b5 = new Button("Boca abajo");
        Button b6 = new Button("Cerrar");

        b1.setOnAction(e -> cartaMonstruoJugarBtn_Click());

        b2.setOnAction(e -> setModoDefensaBtn_Click());

        b3.setOnAction(e -> setModoAtaqueBtn_Click());

        b4.setOnAction(e -> setBocaArribaBtn_Click());

        b5.setOnAction(e -> setBocaAbajoBtn_Click());

        b6.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(b1, b2);

        return vbox;
    }

    // --------------------------------------------------------------------
    // Implementación de botones.
    // --------------------------------------------------------------------
    private void setBocaAbajoBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        this.vista.getControlador().flipBocaAbajo(this.carta, this.jugadorAsociado);
    }

    private void setBocaArribaBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        this.vista.getControlador().flipBocaArriba(this.carta, this.jugadorAsociado);
    }

    private void setModoAtaqueBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        this.vista.getControlador().setModoAtaque(this.carta, this.jugadorAsociado);
    }

    private void setModoDefensaBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        this.vista.getControlador().setModoDefensa(this.carta, this.jugadorAsociado);
    }

    private Object cartaMonstruoJugarBtn_Click()
    {
        // TODO: Implementar.
        return null;
    }

    private void setCartaTrampaBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        // Ojo con las excepciones de región llena.
        this.vista.getControlador().setCartaTrampa(this.carta, this.jugadorAsociado);
    }

    private void setCartaMagicaBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        // Ojo con las excepciones de región llena.
        this.vista.getControlador().setCartaMagica(this.carta, this.jugadorAsociado);
    }

    private void activarCartaMagicaBtn_Click()
    {
        // TODO: Implementar las acciones en caso que larguen excepciones. Ojo que son varias, no son las únicas que
        // aparecen en la interfaz ModeloObservador -> implementar múltiples catch.
        this.vista.getControlador().activarCartaMagica(this.carta, this.jugadorAsociado);
    }
}

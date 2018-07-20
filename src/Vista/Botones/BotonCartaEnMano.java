package Vista.Botones;

import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class BotonCartaEnMano extends Button
{
    //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Carta carta;
    private Button botonDeLaCarta;
    private Vista vista;

    public BotonCartaEnMano(Vista vista, Carta carta)
    {

        this.carta = carta;
        this.vista = vista;

        if (carta.getClass() == CartaMonstruo.class)
        {
            this.botonDeLaCarta = this.crearBotonMonstruoEnMano(vista.obtenerPrimaryStage());
        } else if (carta.getClass() == CartaMagica.class)
        {
            this.botonDeLaCarta = this.crearBotonCartaMagicaEnMano(vista.obtenerPrimaryStage());
        } else
            this.botonDeLaCarta = this.crearBotonCartaTrampaEnMano(vista.obtenerPrimaryStage());
    }

    public Button getBoton()
    {

        return this.botonDeLaCarta;
    }

    private Button crearBotonCartaTrampaEnMano(Stage primaryStage)
    {

        Button boton = new Button();

        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        boton.setPrefSize(anchoDeCarta, altoDeCarta);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaTrampaEnMano(vbox, popup);

        popup.getContent().addAll(vbox);

        boton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.show(primaryStage);
                javafx.geometry.Point2D point = boton.localToScene(0.0, 0.0);
                popup.setX(primaryStage.getX() + point.getX());
                popup.setY(primaryStage.getY() + point.getY());
            }
        });

        return boton;
    }

    private VBox crearVBoxCartaTrampaEnMano(VBox vbox, Popup popup)
    {
        Button b1 = new Button("Jugar");
        Button b2 = new Button("Cerrar");

        b1.setOnAction(e -> jugarCartaTrampaBtn_Click());

        b2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });

        vbox.getChildren().addAll(b1, b2);

        return vbox;
    }

    private Button crearBotonCartaMagicaEnMano(Stage primaryStage)
    {
        Button boton = new Button();

        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        boton.setPrefSize(anchoDeCarta, altoDeCarta);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMagicaEnMano(vbox, popup);

        popup.getContent().addAll(vbox);

        boton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.show(primaryStage);
                javafx.geometry.Point2D point = boton.localToScene(0.0, 0.0);
                popup.setX(primaryStage.getX() + point.getX());
                popup.setY(primaryStage.getY() + point.getY());
            }
        });

        return boton;
    }

    private VBox crearVBoxCartaMagicaEnMano(VBox vbox, Popup popup)
    {
        Button b1 = new Button("Jugar Boca Arriba");
        Button b2 = new Button("Jugar Boca Abajo");
        Button b3 = new Button("Cerrar");

        b1.setOnAction(e -> cartaMagicaJugarBocaArribaBtn_Click());

        b2.setOnAction(e -> cartaMagicaJugarBocaAbajoBtn_Click());

        b3.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });

        vbox.getChildren().addAll(b1, b2, b3);

        return vbox;
    }

    private Button crearBotonMonstruoEnMano(Stage primaryStage)
    {
        Button boton = new Button();

        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        boton.setPrefSize(anchoDeCarta, altoDeCarta);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMonstruoEnMano(vbox, popup, primaryStage);

        popup.getContent().addAll(vbox);

        boton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.show(primaryStage);
                javafx.geometry.Point2D point = boton.localToScene(0.0, 0.0);
                popup.setX(primaryStage.getX() + point.getX());
                popup.setY(primaryStage.getY() + point.getY());
            }
        });

        return boton;
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

        b2.setOnAction(e -> cartaMonstruoOrientarEnDefensaBtn_Click());

        b3.setOnAction(e -> cartaMonstruoOrientarEnAtaqueBtn_Click());

        b4.setOnAction(e -> cartaMonstruoPonerBocaArribaBtn_Click());

        b5.setOnAction(e -> cartaMonstruoPonerBocaAbajoBtn_Click());

        b6.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });

        vbox.getChildren().addAll(b1, b2);

        return vbox;
    }
//-------------------Event Handlers------------------ 

    private void cartaMonstruoPonerBocaAbajoBtn_Click()
    {
        this.vista.obtenerControlador().voltearBocaAbajo(this.carta);
    }

    private void cartaMonstruoPonerBocaArribaBtn_Click()
    {

        this.vista.obtenerControlador().voltearBocaArriba(this.carta);
    }

    private void cartaMonstruoOrientarEnAtaqueBtn_Click()
    {
        this.vista.obtenerControlador().ponerEnModoAtaque(this.carta);
    }

    private void cartaMonstruoOrientarEnDefensaBtn_Click()
    {

        this.vista.obtenerControlador().ponerEnModoDefensa(this.carta);
    }

    private Object cartaMonstruoJugarBtn_Click()
    {
        // TODO Auto-generated method stub
        return null;
    }

    private void jugarCartaTrampaBtn_Click()
    {

        this.vista.obtenerControlador().jugarCartaTrampa(this.carta);
    }

    private void cartaMagicaJugarBocaAbajoBtn_Click()
    {
        this.vista.obtenerControlador().jugarCartaMagicaBocaAbajo(this.carta);
    }

    private void cartaMagicaJugarBocaArribaBtn_Click()
    {
        this.vista.obtenerControlador().jugarCartaMagicaBocaArriba(this.carta);
    }

//----------------------------------------------------
}

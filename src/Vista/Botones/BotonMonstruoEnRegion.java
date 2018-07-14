package Vista.Botones;

import Modelo.carta.monstruo.CartaMonstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class BotonMonstruoEnRegion extends Button
{
    private CartaMonstruo carta;
    private Button botonDeLaCarta;

    public BotonMonstruoEnRegion(CartaMonstruo carta, Stage primaryStage)
    {

        this.carta = carta;
        this.botonDeLaCarta = this.crearBotonMonstruo(primaryStage);
    }

    public Button getBoton()
    {

        return this.botonDeLaCarta;
    }

    private Button crearBotonMonstruo(Stage primaryStage)
    {

        Button boton = new Button();

        boton.setPrefSize(95.4, 139);

        Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMonstruo(vbox, popup);

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

    private VBox crearVBoxCartaMonstruo(VBox vbox, Popup popup)
    {

        Button b2 = new Button("Atacar");
        Button b3 = new Button("Cambiar Orientacion");
        Button b4 = new Button("Dar Vuelta");
        Button b5 = new Button("Cerrar");

        b5.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });

        vbox.getChildren().addAll(b2, b3, b4, b5);

        return vbox;
    }
}

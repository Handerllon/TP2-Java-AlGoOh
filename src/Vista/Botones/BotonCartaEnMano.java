package Vista.Botones;

import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
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
    private Carta carta;
    private Button botonDeLaCarta;
  //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;

    public BotonCartaEnMano(Stage primaryStage, Carta carta)
    {

        this.carta = carta;

        if (carta.getClass() == CartaMonstruo.class)
        {
            this.botonDeLaCarta = this.crearBotonMonstruoEnMano(primaryStage);
        } else if (carta.getClass() == CartaMagica.class)
        {
            this.botonDeLaCarta = this.crearBotonCartaMagicaEnMano(primaryStage);
        } else
            this.botonDeLaCarta = this.crearBotonCartaTrampaEnMano(primaryStage);
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
        Button b2 = new Button("Cerrar");

        b1.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                Popup popupJugar = new Popup();
                VBox vboxJugar = new VBox();
                Button modoAtaque = new Button("En Ataque");
                Button modoDefensa = new Button("En Defensa");
                Button botonCerrar = new Button("Cerrar");

                vboxJugar.getChildren().addAll(modoAtaque, modoDefensa, botonCerrar);

                popupJugar.getContent().addAll(vboxJugar);

                popupJugar.show(primaryStage);
                javafx.geometry.Point2D point = b1.localToScene(0.0, 0.0);
                popupJugar.setX(primaryStage.getX() + point.getX());
                popupJugar.setY(primaryStage.getY() + point.getY());

                modoAtaque.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event)
                    {
                        Popup popupAtaque = new Popup();
                        VBox vboxJugarEnAtaque = new VBox();
                        Button bocaArriba = new Button("Boca Arriba");
                        Button bocaAbajo = new Button("Boca Abajo");
                        Button botonCerrar = new Button("Cerrar");

                        vboxJugarEnAtaque.getChildren().addAll(bocaArriba, bocaAbajo, botonCerrar);

                        popupAtaque.getContent().addAll(vboxJugar);

                        popupAtaque.show(primaryStage);
                        javafx.geometry.Point2D point = modoAtaque.localToScene(0.0, 0.0);
                        popupAtaque.setX(primaryStage.getX() + point.getX());
                        popupAtaque.setY(primaryStage.getY() + point.getY());
                    }
                });
                modoDefensa.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event)
                    {
                        Popup popupAtaque = new Popup();
                        VBox vboxJugarEnAtaque = new VBox();
                        Button bocaArriba = new Button("Boca Arriba");
                        Button bocaAbajo = new Button("Boca Abajo");
                        Button botonCerrar = new Button("Cerrar");

                        vboxJugarEnAtaque.getChildren().addAll(bocaArriba, bocaAbajo, botonCerrar);

                        popupAtaque.getContent().addAll(vboxJugar);

                        popupAtaque.show(primaryStage);
                        javafx.geometry.Point2D point = modoDefensa.localToScene(0.0, 0.0);
                        popupAtaque.setX(primaryStage.getX() + point.getX());
                        popupAtaque.setY(primaryStage.getY() + point.getY());
                    }
                });
            }
        });

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
}

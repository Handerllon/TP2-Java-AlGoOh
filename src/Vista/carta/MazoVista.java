package Vista.carta;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MazoVista implements ObservadorDeModelo
{
    //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Stage stage;
    private Modelo modelo;
    private Button mazoJugador;
    private Button mazoOponente;

    public MazoVista(Stage primaryStage, Modelo modelo)
    {

        this.modelo = modelo;

        stage = primaryStage;

        this.mazoJugador = inicializar();
        this.mazoOponente = inicializar();
    }

    public Button inicializar()
    {

        Button boton = new Button();

        boton.setPrefSize(anchoDeCarta, altoDeCarta);
        boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource("resources/imagenes/tablero/Back.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        Image image = new Image(getClass().getClassLoader()
                .getResource("resources/imagenes/tablero/Back.jpg").toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        boton.setTooltip(tp);

        return boton;
    }

    public Button getMazoJugador()
    {

        return mazoJugador;
    }

    public Button getMazoOponente()
    {

        return mazoOponente;
    }

    @Override
    public void actualizarEstado()
    {
        this.actualizarMazoJugador(this.modelo.obtenerNumeroDeCartasRestantesEnMazoJugador());
        this.actualizarMazoOponente(this.modelo.obtenerNumeroDeCartasRestantesEnMazoOponente());
    }

    private void actualizarMazoJugador(int numeroDeCartas)
    {

        Popup popup = new Popup();
        Button b2 = new Button("Cartas restantes en el mazo: \n" + Integer.valueOf(numeroDeCartas).toString());
        popup.getContent().add(b2);

        this.mazoJugador.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.show(stage);
                javafx.geometry.Point2D point = mazoJugador.localToScene(0.0, 0.0);
                popup.setX(stage.getX() + point.getX());
                popup.setY(stage.getY() + point.getY());
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });
    }

    private void actualizarMazoOponente(int numeroDeCartas)
    {

        Popup popup = new Popup();
        Button b2 = new Button("Cartas restantes en el mazo: \n" + Integer.valueOf(numeroDeCartas).toString());
        popup.getContent().add(b2);

        this.mazoOponente.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.show(stage);
                javafx.geometry.Point2D point = mazoOponente.localToScene(0.0, 0.0);
                popup.setX(stage.getX() + point.getX());
                popup.setY(stage.getY() + point.getY());
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });
    }
}

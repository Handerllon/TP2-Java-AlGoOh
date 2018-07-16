package Vista.Botones;

import Modelo.carta.campo.CartaCampo;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class BotonCampoEnRegion extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Button boton;
    private CartaCampo carta;
    private Stage stage;

    public BotonCampoEnRegion(Stage primaryStage)
    {

        this.stage = primaryStage;

        boton = new Button();

        // TODO: generalizar el hardcodeo de los numeros.
        boton.setPrefSize(anchoDeCarta, altoDeCarta);
        boton.setStyle(estiloRegion);
    }

    public Button obtenerBoton()
    {

        return boton;
    }

    public void clear()
    {

        this.boton = new Button();
        boton.setStyle(estiloRegion);
        this.boton.setPrefSize(anchoDeCarta, altoDeCarta);
    }

    public void actualizarImagen(CartaCampo unaCartaCampo)
    {

        this.carta = unaCartaCampo;
        this.boton = this.crearBotonParaCartaEnRegion();
        Image image = new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString());
        Tooltip tp = new Tooltip();
        tp.setGraphic(new ImageView(image));

        this.boton.setTooltip(tp);
    }

    private Button crearBotonParaCartaEnRegion()
    {
        Button botonEnRegion = new Button();

        //TODO: Hacer opciones que tiene una cartaCampo una vez que fue jugada
        botonEnRegion.setPrefSize(anchoDeCarta, altoDeCarta);
        botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        return botonEnRegion;
    }
}

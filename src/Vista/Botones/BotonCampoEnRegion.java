package Vista.Botones;

import Modelo.Jugador;
import Modelo.carta.campo.CartaCampo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;

public class BotonCampoEnRegion extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    // TODO: número mágico.
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Button boton;
    private CartaCampo carta;
    private Vista vista;
    private Jugador jugadorAsociado;

    public BotonCampoEnRegion(Vista vista, Jugador jugadorAsociado)
    {
        this.jugadorAsociado = jugadorAsociado;
        this.vista = vista;

        boton = new Button();

        // TODO: generalizar el hardcodeo de los numeros.
        boton.setPrefSize(anchoDeCarta, altoDeCarta);
        boton.setStyle(estiloRegion);
    }

    public Button getBoton()
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
                .getResource(this.carta.getLocacionDeImagen()).toString());
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
                .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        return botonEnRegion;
    }
}

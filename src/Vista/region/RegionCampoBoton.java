package Vista.region;

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

public class RegionCampoBoton extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    // TODO: número mágico.
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Button boton;
    private CartaCampo carta;
    private Vista vista;
    private Jugador jugadorAsociado;

    public RegionCampoBoton(Vista vista, Jugador jugadorAsociado)
    {
        this.jugadorAsociado = jugadorAsociado;
        this.vista = vista;

        boton = new Button();

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

    public void actualizarImagen(CartaCampo carta)
    {

        this.carta = carta;
        this.boton = this.crearBotonParaCartaEnRegion();
        Image image = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());
        Tooltip tooltip = new Tooltip();
        tooltip.setGraphic(new ImageView(image));

        this.boton.setTooltip(tooltip);
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

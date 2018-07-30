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

public class RegionesCampoBoton extends Button
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    // Se uso como base una resolucion de 1920x1080 para los tamanos
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Button boton;
    private CartaCampo carta;
    private Vista vista;
    private Jugador jugadorAsociado;
    private Image imagenBoton;
    private Tooltip tooltip;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCampoBoton(Vista vista, Jugador jugadorAsociado)
    {
        this.jugadorAsociado = jugadorAsociado;
        this.vista = vista;

        this.boton = new Button();

        this.boton.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta,
        		this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
        this.boton.setStyle(estiloRegion);

        this.tooltip = new Tooltip();
    }

    public Button getBoton()
    {
        return boton;
    }

    public void clear()
    {
        this.boton = new Button();
        boton.setStyle(estiloRegion);
        this.boton.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta,
        		this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
    }

    public void actualizarImagen(CartaCampo carta)
    {
        this.carta = carta;

        //TODO: Hacer opciones que tiene una cartaCampo una vez que fue jugada
        this.boton.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta,
        		this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
        this.boton.setStyle(null);
        this.boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        this.imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());

        this.tooltip.setGraphic(new ImageView(this.imagenBoton));

        this.boton.setTooltip(this.tooltip);
    }
}

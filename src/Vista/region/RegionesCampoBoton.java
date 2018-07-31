package Vista.region;

import Modelo.Jugador;
import Modelo.carta.campo.CartaCampo;
import Modelo.observadores.ObservadorDeModelo;
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

import java.util.ArrayList;

public class RegionesCampoBoton extends Button implements ObservadorDeModelo
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

        // TODO: implementar el observer pero para cartas campo así no se actualiza cada vez que se mueven otras cartas.
        vista.getModelo().registrarObsevador(this);

        actualizar();
    }

    public Button getBoton()
    {
        return boton;
    }

    public void actualizar()
    {
        this.tooltip = new Tooltip();

        this.boton = new Button();
        this.boton.setStyle(estiloRegion);
        this.boton.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);

        ArrayList<CartaCampo> cartasCampoJugador = this.vista.getModelo().getCartasEnRegionCampoDe(jugadorAsociado);
        for (int i = 0; i < cartasCampoJugador.size(); i++)
        {

            this.carta = cartasCampoJugador.get(i);

            this.boton.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                    this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
            this.boton.setStyle(null);
            this.boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                    .getResource(this.carta.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

            this.imagenBoton = new Image(getClass().getClassLoader().getResource(this.carta.getLocacionDeImagen()).toString());

            this.tooltip.setGraphic(new ImageView(this.imagenBoton));

            this.boton.setTooltip(this.tooltip);
        }

        this.vista.actualizarDibujo();
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void seTomoCartaDeMazo()
    {

    }

    @Override
    public void ingresoCartaAMano()
    {

    }

    @Override
    public void egresoCartaAMano()
    {

    }

    @Override
    public void ingresoCartaARegion()
    {
        this.actualizar();
    }

    @Override
    public void egresoCartaARegion()
    {
        this.actualizar();
    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {
        this.actualizar();
    }

    @Override
    public void cartaCambioDeModo()
    {

    }
}

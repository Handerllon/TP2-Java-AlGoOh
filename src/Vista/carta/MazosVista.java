package Vista.carta;

import Modelo.Jugador;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;

public class MazosVista implements ObservadorDeModelo
{
	private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;;
    // Se uso como base una resolucion de 1920x1080
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private Vista vista;
    private Button mazoJugador;
    private Tooltip toolTipJugador;
    private Button mazoOponente;
    private Tooltip toolTipOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public MazosVista(Vista vista)
    {
        this.vista = vista;
        // TODO: Para cuando se implemente los observadores puntuales:
        // vista.getModelo().registrarObsevador(this);

        this.toolTipJugador = new Tooltip();
        this.toolTipOponente = new Tooltip();

        this.mazoJugador = crearBotonMazo(this.vista.getModelo().getJugador());
        this.mazoOponente = crearBotonMazo(this.vista.getModelo().getOponente());

        this.mazoJugador.setTooltip(toolTipJugador);
        this.mazoOponente.setTooltip(toolTipOponente);

        this.huboCambios();
    }

    public Button crearBotonMazo(Jugador jugadorAsociado)
    {

        Button boton = new Button();

        boton.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta, this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
        boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(rutaImagenReversoCarta).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

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

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void huboCambios()
    {
        this.actualizarMazoJugador(this.vista.getModelo().getCantidadCartasRestantesMazoJugador());
        this.actualizarMazoOponente(this.vista.getModelo().getCantidadCartasRestantesMazoOponente());
    }

    private void actualizarMazoJugador(int numeroDeCartas)
    {
        this.toolTipJugador.setText(Integer.toString(numeroDeCartas));
    }

    private void actualizarMazoOponente(int numeroDeCartas)
    {
        this.toolTipOponente.setText(Integer.toString(numeroDeCartas));
    }

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
    public void cambiaronLosPuntosDeVida()
    {

    }
}

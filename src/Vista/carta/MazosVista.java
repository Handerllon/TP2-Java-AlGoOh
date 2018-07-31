package Vista.carta;

import Modelo.Jugador;
import Modelo.ModeloObservable;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;

import java.net.URL;

public class MazosVista implements ObservadorDeModelo
{
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    ;
    // Se uso como base una resolucion de 1920x1080
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private Vista vista;
    private ModeloObservable modelo;
    private Button mazoJugador;
    private Tooltip toolTipJugador;
    private Button mazoOponente;
    private Tooltip toolTipOponente;
    private AudioClip audioClipCardDraw;
    private double cardDrawVolume = 1;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public MazosVista(Vista vista)
    {
        this.vista = vista;
        this.modelo = vista.getModelo();

        this.modelo.registrarObsevador(this);

        this.toolTipJugador = new Tooltip();
        this.toolTipOponente = new Tooltip();

        this.mazoJugador = crearBotonMazo(this.modelo.getJugador());
        this.mazoOponente = crearBotonMazo(this.modelo.getOponente());

        this.mazoJugador.setTooltip(toolTipJugador);
        this.mazoOponente.setTooltip(toolTipOponente);

        // -------------------------------
        // Multimedia del botón.
        // -------------------------------
        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_draw.wav");
        this.audioClipCardDraw = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardDraw.setVolume(cardDrawVolume);
    }

    public Button crearBotonMazo(Jugador jugadorAsociado)
    {

        Button boton = new Button();

        boton.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta, this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
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
        audioClipCardDraw.play();
        this.actualizarMazoJugador(this.modelo.getCantidadCartasRestantesMazoDe(this.modelo.getJugador()));
        this.actualizarMazoOponente(this.modelo.getCantidadCartasRestantesMazoDe(this.modelo.getOponente()));
        this.vista.actualizarDibujo();
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

    }

    @Override
    public void egresoCartaARegion()
    {

    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {

    }

    @Override
    public void cartaCambioDeModo()
    {

    }
}

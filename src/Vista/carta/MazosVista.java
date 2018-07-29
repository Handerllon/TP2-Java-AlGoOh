package Vista.carta;

import Controlador.excepciones.NoSePuedeTomarCartaError;
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
    private static double anchoDeCarta;
    private static double altoDeCarta;
    // TODO: número mágico.
    private static double relacionAnchoAlto = 1.457;
    private static double relacionAnchoCartaPantalla = 19.92;
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

        this.anchoDeCarta = vista.getResolucionHorizontal() / relacionAnchoCartaPantalla;
        this.altoDeCarta = this.anchoDeCarta * relacionAnchoAlto;

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

        boton.setPrefSize(anchoDeCarta, altoDeCarta);
        boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(rutaImagenReversoCarta).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        boton.setOnAction(e -> tomarCartaBtn_Click(jugadorAsociado));

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
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void tomarCartaBtn_Click(Jugador jugador)
    {
        try
        {
            this.vista.getControlador().tomarCarta(jugador);
        } catch (NoSePuedeTomarCartaError error)
        {
            this.vista.mostrarError(error);
        }
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
}

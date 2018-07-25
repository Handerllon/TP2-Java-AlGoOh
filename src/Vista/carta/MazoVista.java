package Vista.carta;

import Modelo.Jugador;
import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;

public class MazoVista implements ObservadorDeModelo
{
    private static double anchoDeCarta;
    private static double altoDeCarta;
    // TODO: número mágico.
    private static double relacionAnchoAlto = 1.457;
    private static double relacionAnchoCartaPantalla = 19.92;
    private Vista vista;
    private Button mazoJugador;
    private Tooltip toolTipJugador;
    private Button mazoOponente;
    private Tooltip toolTipOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public MazoVista(Vista vista)
    {
        this.anchoDeCarta = Screen.getPrimary().getVisualBounds().getWidth() / relacionAnchoCartaPantalla;
        this.altoDeCarta = this.anchoDeCarta * relacionAnchoAlto;

        this.vista = vista;

        this.toolTipJugador = new Tooltip();
        this.toolTipOponente = new Tooltip();

        this.mazoJugador = inicializar(this.vista.getModelo().getJugador());
        this.mazoJugador.setTooltip(toolTipJugador);
        this.mazoOponente = inicializar(this.vista.getModelo().getOponente());
        this.mazoOponente.setTooltip(toolTipOponente);

        this.actualizarEstado();
    }

    public Button inicializar(Jugador jugadorAsociado)
    {

        Button boton = new Button();

        boton.setPrefSize(anchoDeCarta, altoDeCarta);
        boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource("resources/imagenes/tablero/Back.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        boton.setOnAction(e -> tomarCartaBtn_Click(jugadorAsociado));

        return boton;
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
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

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void tomarCartaBtn_Click(Jugador jugador)
    {
        this.vista.getControlador().tomarCarta(jugador);
    }
}

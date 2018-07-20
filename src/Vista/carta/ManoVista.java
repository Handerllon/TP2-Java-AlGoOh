package Vista.carta;

import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ManoVista implements ObservadorDeModelo
{
    private Vista vista;
    private Stage stage;
    private FlowPaneDeMano manoJugador;
    private FlowPaneDeMano manoOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public ManoVista(Stage primaryStage, Vista vista)
    {

        this.stage = primaryStage;
        this.vista = vista;

        this.manoJugador = new FlowPaneDeMano(this.vista);
        this.manoOponente = new FlowPaneDeMano(this.vista);
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    public FlowPane getManoJugador()
    {

        return manoJugador.getFlowPane();
    }

    public FlowPane getManoOponente()
    {

        return manoOponente.getFlowPane();
    }

    @Override
    public void actualizarEstado()
    {
        this.manoJugador.clear();
        this.manoOponente.clear();
        this.manoJugador.actualizarMano(this.vista.obtenerModelo().obtenerCartasEnLaManoDelJugador());
        this.manoOponente.actualizarMano(this.vista.obtenerModelo().obtenerCartasEnLaManoDelOponente());
    }
}

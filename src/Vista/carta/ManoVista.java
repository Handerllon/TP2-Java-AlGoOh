package Vista.carta;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ManoVista implements ObservadorDeModelo
{
    private Modelo modelo;
    private Stage stage;
    private FlowPaneDeMano manoJugador;
    private FlowPaneDeMano manoOponente;

    public ManoVista(Stage primaryStage, Modelo modelo)
    {

        this.stage = primaryStage;
        this.modelo = modelo;

        this.manoJugador = new FlowPaneDeMano(stage);
        this.manoOponente = new FlowPaneDeMano(stage);
    }

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
        this.manoJugador.actualizarMano(this.modelo.obtenerCartasEnLaManoDelJugador());
        this.manoOponente.actualizarMano(this.modelo.obtenerCartasEnLaManoDelOponente());
    }
}

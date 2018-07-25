package Vista.carta;

import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.FlowPane;

public class ManoVista implements ObservadorDeModelo
{
    private Vista vista;
    private FlowPaneDeMano manoJugador;
    private FlowPaneDeMano manoOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public ManoVista(Vista vista)
    {
        this.vista = vista;

        this.manoJugador = new FlowPaneDeMano(this.vista, this.vista.getModelo().getJugador());
        this.manoOponente = new FlowPaneDeMano(this.vista, this.vista.getModelo().getJugador());
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
        this.manoJugador.actualizarMano(this.vista.getModelo().getCartasManoJugador());
        this.manoOponente.actualizarMano(this.vista.getModelo().getCartasManoOponente());
    }
}

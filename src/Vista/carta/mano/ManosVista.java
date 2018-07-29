package Vista.carta.mano;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.FlowPane;

public class ManosVista implements ObservadorDeModelo
{
    private Vista vista;
    private ManosFlowPane manoJugador;
    private ManosFlowPane manoOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public ManosVista(Vista vista)
    {
        this.vista = vista;

        this.manoJugador = new ManosFlowPane(this.vista, this.vista.getModelo().getJugador());
        this.manoOponente = new ManosFlowPane(this.vista, this.vista.getModelo().getOponente());
    }

    public FlowPane getManoJugador()
    {

        return manoJugador.getFlowPane();
    }

    public FlowPane getManoOponente()
    {

        return manoOponente.getFlowPane();
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void huboCambios()
    {
        this.manoJugador.clear();
        this.manoOponente.clear();
        this.manoJugador.actualizar(this.vista.getModelo().getCartasManoJugador());
        this.manoOponente.actualizar(this.vista.getModelo().getCartasManoOponente());
    }
}

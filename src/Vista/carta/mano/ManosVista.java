package Vista.carta.mano;

import Modelo.ModeloObservable;
import Vista.Vista;
import javafx.scene.layout.FlowPane;

public class ManosVista
{
    private Vista vista;
    private ModeloObservable modelo;
    private ManosFlowPane manoJugador;
    private ManosFlowPane manoOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public ManosVista(Vista vista)
    {
        this.vista = vista;
        this.modelo = vista.getModelo();

        //vista.getModelo().registrarObsevador(this);

        this.manoJugador = new ManosFlowPane(this.vista, this.modelo.getJugador());
        this.manoOponente = new ManosFlowPane(this.vista, this.modelo.getOponente());
    }

    public FlowPane getManoJugador()
    {

        return manoJugador.getFlowPane();
    }

    public FlowPane getManoOponente()
    {

        return manoOponente.getFlowPane();
    }
}

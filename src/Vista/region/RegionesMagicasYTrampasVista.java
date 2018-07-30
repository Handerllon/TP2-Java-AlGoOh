package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionesMagicasYTrampasVista implements ObservadorDeModelo
{
    private Vista vista;
    private RegionesMagicasYTrampasGrid gridJugador;
    private RegionesMagicasYTrampasGrid gridOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMagicasYTrampasVista(Vista vista)
    {
        this.vista = vista;
        // TODO: Para cuando se implemente los observadores puntuales:
        // vista.getModelo().registrarObsevador(this);

        this.gridJugador = new RegionesMagicasYTrampasGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new RegionesMagicasYTrampasGrid(this.vista, this.vista.getModelo().getOponente());
    }

    public GridPane getGridJugador()
    {

        return gridJugador.getGrid();
    }

    public GridPane getGridOponente()
    {

        return gridOponente.getGrid();
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void huboCambios()
    {
        this.gridJugador.clear();
        this.gridOponente.clear();
        this.gridJugador.actualizarRegion(this.vista.getModelo().getCartasEnRegionMagicasYTrampasJugador());
        this.gridOponente.actualizarRegion(this.vista.getModelo().getCartasEnRegionMagicasYTrampasOponente());
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

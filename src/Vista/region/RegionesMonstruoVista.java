package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionesMonstruoVista implements ObservadorDeModelo
{
    private Vista vista;
    private RegionesMonstruosGrid gridJugador;
    private RegionesMonstruosGrid gridOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruoVista(Vista vista)
    {
        this.vista = vista;
        // TODO: Para cuando se implemente los observadores puntuales:
        // vista.getModelo().registrarObsevador(this);

        this.gridJugador = new RegionesMonstruosGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new RegionesMonstruosGrid(this.vista, this.vista.getModelo().getOponente());
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
        this.gridJugador.actualizar(this.vista.getModelo().getCartasEnRegionMonstruosJugador());
        this.gridOponente.actualizar(this.vista.getModelo().getCartasEnRegionMonstruosOponente());
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

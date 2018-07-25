package Vista.areaDeJuego;

import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionMagicasYTrampasVista implements ObservadorDeModelo
{
    private Vista vista;
    private GridDeRegionMagicasYTrampas gridJugador;
    private GridDeRegionMagicasYTrampas gridOponente;

    public RegionMagicasYTrampasVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador = new GridDeRegionMagicasYTrampas(this.vista, this.vista.obtenerModelo().obtenerJugador());
        this.gridOponente = new GridDeRegionMagicasYTrampas(this.vista, this.vista.obtenerModelo().obtenerOponente());
    }

    public GridPane getGridJugador()
    {

        return gridJugador.obtenerGrid();
    }

    public GridPane getGridOponente()
    {

        return gridOponente.obtenerGrid();
    }

    @Override
    public void actualizarEstado()
    {
        this.gridJugador.clear();
        this.gridOponente.clear();
        this.gridJugador.actualizarRegion(this.vista.obtenerModelo().obtenerCartasEnRegionMagicasYTrampasJugador());
        this.gridOponente.actualizarRegion(this.vista.obtenerModelo().obtenerCartasEnRegionMagicasYTrampasOponente());
    }
}

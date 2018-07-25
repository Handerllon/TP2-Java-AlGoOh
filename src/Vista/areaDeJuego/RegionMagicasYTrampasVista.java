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

        this.gridJugador = new GridDeRegionMagicasYTrampas(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new GridDeRegionMagicasYTrampas(this.vista, this.vista.getModelo().getOponente());
    }

    public GridPane getGridJugador()
    {

        return gridJugador.getGrid();
    }

    public GridPane getGridOponente()
    {

        return gridOponente.getGrid();
    }

    @Override
    public void actualizarEstado()
    {
        this.gridJugador.clear();
        this.gridOponente.clear();
        this.gridJugador.actualizarRegion(this.vista.getModelo().getCartasEnRegionMagicasYTrampasJugador());
        this.gridOponente.actualizarRegion(this.vista.getModelo().getCartasEnRegionMagicasYTrampasOponente());
    }
}

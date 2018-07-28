package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionMonstruosVista implements ObservadorDeModelo
{
    private Vista vista;
    private RegionMonstruosGrid gridJugador;
    private RegionMonstruosGrid gridOponente;

    public RegionMonstruosVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador = new RegionMonstruosGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new RegionMonstruosGrid(this.vista, this.vista.getModelo().getOponente());
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
    public void huboCambios()
    {
        this.gridJugador.clear();
        this.gridOponente.clear();
        this.gridJugador.actualizar(this.vista.getModelo().getCartasEnRegionMonstruosJugador());
        this.gridOponente.actualizar(this.vista.getModelo().getCartasEnRegionMonstruosOponente());
    }
}

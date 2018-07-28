package Vista.areaDeJuego;

import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionMonstruosVista implements ObservadorDeModelo
{
    private Vista vista;
    private GridDeRegionMonstruos gridJugador;
    private GridDeRegionMonstruos gridOponente;

    public RegionMonstruosVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador = new GridDeRegionMonstruos(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new GridDeRegionMonstruos(this.vista, this.vista.getModelo().getOponente());
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
    public void actualizar()
    {
        this.gridJugador.clear();
        this.gridOponente.clear();
        this.gridJugador.actualizar(this.vista.getModelo().getCartasEnRegionMonstruosJugador());
        this.gridOponente.actualizar(this.vista.getModelo().getCartasEnRegionMonstruosOponente());
    }
}

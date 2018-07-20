package Vista.areaDeJuego;

import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegionMonstruosVista implements ObservadorDeModelo
{
    private Vista vista;
    private Stage stage;
    private GridDeRegionMonstruos gridJugador;
    private GridDeRegionMonstruos gridOponente;

    public RegionMonstruosVista(Stage primaryStage, Vista vista)
    {
        this.stage = primaryStage;
        this.vista = vista;

        this.gridJugador = new GridDeRegionMonstruos(this.vista);
        this.gridOponente = new GridDeRegionMonstruos(this.vista);
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
        this.gridJugador.actualizarRegion(this.vista.obtenerModelo().obtenerCartasEnRegionMonstruosJugador());
        this.gridOponente.actualizarRegion(this.vista.obtenerModelo().obtenerCartasEnRegionMonstruosOponente());
    }
}

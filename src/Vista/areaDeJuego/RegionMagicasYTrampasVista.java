package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegionMagicasYTrampasVista implements ObservadorDeModelo
{
    private Stage stage;
    private Vista vista;
    private GridDeRegionMagicasYTrampas gridJugador;
    private GridDeRegionMagicasYTrampas gridOponente;

    public RegionMagicasYTrampasVista(Stage primaryStage, Vista vista)
    {
        this.stage = primaryStage;
        this.vista = vista;

        this.gridJugador = new GridDeRegionMagicasYTrampas(this.stage);
        this.gridOponente = new GridDeRegionMagicasYTrampas(this.stage);
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

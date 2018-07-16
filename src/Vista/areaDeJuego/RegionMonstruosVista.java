package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegionMonstruosVista implements ObservadorDeModelo
{
    private Modelo modelo;
    private Stage stage;
    private GridDeRegionMonstruos gridJugador;
    private GridDeRegionMonstruos gridOponente;

    public RegionMonstruosVista(Stage primaryStage, Modelo modelo)
    {
        this.stage = primaryStage;
        this.modelo = modelo;

        this.gridJugador = new GridDeRegionMonstruos(this.stage);
        this.gridOponente = new GridDeRegionMonstruos(this.stage);
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
    public void actualizar()
    {
        this.gridJugador.clear();
        this.gridOponente.clear();
        this.gridJugador.actualizarRegion(this.modelo.obtenerCartasEnRegionMonstruosJugador());
        this.gridOponente.actualizarRegion(this.modelo.obtenerCartasEnRegionMonstruosOponente());
    }
}

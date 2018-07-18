package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegionMagicasYTrampasVista implements ObservadorDeModelo
{
    private Stage stage;
    private Modelo modelo;
    private GridDeRegionMagicasYTrampas gridJugador;
    private GridDeRegionMagicasYTrampas gridOponente;

    public RegionMagicasYTrampasVista(Stage primaryStage, Modelo modelo)
    {
        this.stage = primaryStage;
        this.modelo = modelo;

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
        this.gridJugador.actualizarRegion(this.modelo.obtenerCartasEnRegionMagicasYTrampasJugador());
        this.gridOponente.actualizarRegion(this.modelo.obtenerCartasEnRegionMagicasYTrampasOponente());
    }
}

package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorModelo;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class RegionMagicasYTrampasVista implements ObservadorModelo
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
    public void actualizar()
    {
    	this.gridJugador.clear();
    	this.gridOponente.clear();
    	this.gridJugador.actualizarRegion(this.modelo.obtenerCartasEnRegionMagicasYTrampasJugador());
    	this.gridOponente.actualizarRegion(this.modelo.obtenerCartasEnRegionMagicasYTrampasOponente());
    }
}

package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorModelo;
import Vista.Botones.BotonMonstruoEnRegion;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class RegionMonstruosVista implements ObservadorModelo
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

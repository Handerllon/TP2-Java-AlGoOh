package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorModelo;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class RegionMonstruosVista implements ObservadorModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
    private Modelo modelo;
    private Stage stage;
    private GridPane gridJugador;
    private GridPane gridOponente;

    public RegionMonstruosVista(Stage primaryStage, Modelo modelo)
    {
        gridJugador = this.inicializarGrid();
        gridOponente = this.inicializarGrid();
        stage = primaryStage;
    }

    public GridPane inicializarGrid()
    {

        GridPane grid = new GridPane();

        // TODO: generalizar el hardcodeo de los numeros.
        ColumnConstraints columna0 = new ColumnConstraints(262);
        ColumnConstraints columna1 = new ColumnConstraints(262);
        ColumnConstraints columna2 = new ColumnConstraints(262);
        ColumnConstraints columna3 = new ColumnConstraints(262);
        ColumnConstraints columna4 = new ColumnConstraints(262);
        RowConstraints fila1 = new RowConstraints(160);

        grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        grid.getRowConstraints().addAll(fila1);

        for (int i = 0; i < 5; i++)
        {

            Button boton = new Button();
            boton.setPrefSize(95.4, 139);
            boton.setStyle(estiloRegion);

            grid.add(boton, i, 0);

            GridPane.setHalignment(boton, HPos.CENTER);
        }

        grid.setGridLinesVisible(true);

        return grid;
    }

    public GridPane getGridJugador()
    {

        return gridJugador;
    }

    public GridPane getGridOponente()
    {

        return gridOponente;
    }

    @Override
    public void actualizar()
    {

    }
}

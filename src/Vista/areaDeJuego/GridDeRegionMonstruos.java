package Vista.areaDeJuego;

import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Botones.BotonMonstruoEnRegion;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GridDeRegionMonstruos extends GridPane
{
    private GridPane grid;
    private Stage stage;
    private ArrayList<BotonMonstruoEnRegion> botones;

    public GridDeRegionMonstruos(Stage primaryStage)
    {

        this.stage = primaryStage;

        this.grid = new GridPane();

        this.botones = new ArrayList<BotonMonstruoEnRegion>();

        // TODO: generalizar el hardcodeo de los numeros.
        ColumnConstraints columna0 = new ColumnConstraints(262);
        ColumnConstraints columna1 = new ColumnConstraints(262);
        ColumnConstraints columna2 = new ColumnConstraints(262);
        ColumnConstraints columna3 = new ColumnConstraints(262);
        ColumnConstraints columna4 = new ColumnConstraints(262);
        RowConstraints fila1 = new RowConstraints(160);

        this.grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        this.grid.getRowConstraints().addAll(fila1);

        this.crearListaDeBotones();

        for (int i = 0; i < botones.size(); i++)
        {
            this.grid.add(botones.get(i).obtenerBoton(), i, 0);

            this.grid.setHalignment(botones.get(i).obtenerBoton(), HPos.CENTER);
        }
    }

    private void crearListaDeBotones()
    {

        for (int i = 0; i < 5; i++)
        {

            BotonMonstruoEnRegion boton = new BotonMonstruoEnRegion(stage);

            botones.add(boton);
        }
    }

    public GridPane obtenerGrid()
    {

        return this.grid;
    }

    public void clear()
    {

        for (int i = 0; i < botones.size(); i++)
        {
            botones.get(i).clear();
        }
    }

    public void actualizarRegion(ArrayList<CartaMonstruo> cartasEnRegionMonstruos)
    {

        for (int i = 0; i < cartasEnRegionMonstruos.size(); i++)
        {
            botones.get(i).actualizar(cartasEnRegionMonstruos.get(i));
        }
    }
}
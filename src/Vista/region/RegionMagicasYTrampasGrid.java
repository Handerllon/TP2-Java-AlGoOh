package Vista.region;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class RegionMagicasYTrampasGrid extends GridPane
{
    public static double anchoColumna;
    // TODO: número mágico.
    public static double relacionAnchoColumnaPantalla = 7.25;
    private GridPane grid;
    private ArrayList<RegionMagicasYTrampasBoton> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    public RegionMagicasYTrampasGrid(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;

        this.vista = vista;

        this.grid = new GridPane();

        this.botones = new ArrayList<RegionMagicasYTrampasBoton>();

        anchoColumna = vista.getResolucionHorizontal() / relacionAnchoColumnaPantalla;
        ColumnConstraints columna0 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna1 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna2 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna3 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna4 = new ColumnConstraints(anchoColumna);
        // TODO: número mágico.
        RowConstraints fila1 = new RowConstraints(160);

        this.grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        this.grid.getRowConstraints().addAll(fila1);

        this.crearListaDeBotones();

        for (int i = 0; i < botones.size(); i++)
        {
            this.grid.add(botones.get(i).getBoton(), i, 0);

            this.grid.setHalignment(botones.get(i).getBoton(), HPos.CENTER);
        }
    }

    private void crearListaDeBotones()
    {

        for (int i = 0; i < 5; i++)
        {

            RegionMagicasYTrampasBoton boton = new RegionMagicasYTrampasBoton(this.vista, this.jugadorAsociado);

            botones.add(boton);
        }
    }

    public GridPane getGrid()
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

    public void actualizarRegion(ArrayList<Carta> cartasEnRegionMagicasYTrampas)
    {

        for (int i = 0; i < cartasEnRegionMagicasYTrampas.size(); i++)
        {
            botones.get(i).actualizar(cartasEnRegionMagicasYTrampas.get(i));
        }
    }
}

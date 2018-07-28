package Vista.region;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class RegionMonstruosGrid extends GridPane
{
    private static double anchoColumna;
    // TODO: número mágico.
    private final double relacionAnchoColumnaPantalla = 7.25;
    private final double anchoFila = 160;
    private final int cantidadBotonesGrid = 5;
    private GridPane grid;
    private ArrayList<RegionMonstruoBoton> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    public RegionMonstruosGrid(Vista vista, Jugador jugador)
    {

        this.jugadorAsociado = jugador;

        this.vista = vista;

        this.grid = new GridPane();

        this.botones = new ArrayList<>();

        anchoColumna = vista.getResolucionHorizontal() / relacionAnchoColumnaPantalla;
        ColumnConstraints columna0 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna1 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna2 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna3 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna4 = new ColumnConstraints(anchoColumna);

        RowConstraints fila1 = new RowConstraints(anchoFila);

        this.grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        this.grid.getRowConstraints().addAll(fila1);

        RegionMonstruoBoton boton;
        for (int i = 0; i < cantidadBotonesGrid; i++)
        {
            boton = new RegionMonstruoBoton(this.vista, this.jugadorAsociado);
            botones.add(boton);
            // Se posicionan los botones en la grilla de la región monstruo.
            this.grid.add(boton.getBoton(), i, 0);
            this.grid.setHalignment(boton.getBoton(), HPos.CENTER);
        }
    }

    public GridPane getGrid()
    {

        return this.grid;
    }

    public void clear()
    {
        this.botones.forEach(boton -> boton.clear());
    }

    public void actualizar(ArrayList<CartaMonstruo> cartasEnRegionMonstruos)
    {
        // TODO: reemplazar por lambda usando un iterator de cartasEnRegionMonstruo.
        for (int i = 0; i < cartasEnRegionMonstruos.size(); i++)
        {
            botones.get(i).actualizar(cartasEnRegionMonstruos.get(i));
        }
    }
}

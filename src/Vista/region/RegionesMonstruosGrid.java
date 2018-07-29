package Vista.region;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class RegionesMonstruosGrid extends GridPane
{
    private static double anchoColumna;
    // Se uso como base una resolucion de 1920x1080
    private final double relacionAnchoColumnaPantalla = 7.25;
    private final int cantidadBotonesGrid = 5;
    private GridPane grid;
    private ArrayList<RegionesMonstruoBoton> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruosGrid(Vista vista, Jugador jugador)
    {

        this.jugadorAsociado = jugador;

        this.vista = vista;

        this.grid = new GridPane();

        this.botones = new ArrayList<>();

        anchoColumna = (vista.getResolucionHorizontal() / relacionAnchoColumnaPantalla) / 1.7;
        ColumnConstraints columna0 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna1 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna2 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna3 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna4 = new ColumnConstraints(anchoColumna);

        RowConstraints fila1 = new RowConstraints((this.vista.getResolucionHorizontal() * 160) / 1920);

        this.grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        this.grid.getRowConstraints().addAll(fila1);

        this.grid.setAlignment(Pos.CENTER);

        RegionesMonstruoBoton boton;
        for (int i = 0; i < cantidadBotonesGrid; i++)
        {
            boton = new RegionesMonstruoBoton(this.vista, this.jugadorAsociado);
            botones.add(boton);
            // Se posicionan los botones en la grilla de la región monstruo.
            this.grid.add(boton.getBoton(), i, 0);
            this.grid.setHalignment(boton.getBoton(), HPos.CENTER);
        }
    }

    public void clear()
    {
        // TODO: Ver como hacer mas lindo esto
        this.botones = new ArrayList<>();
        RegionesMonstruoBoton boton;
        for (int i = 0; i < cantidadBotonesGrid; i++)
        {
            boton = new RegionesMonstruoBoton(this.vista, this.jugadorAsociado);
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

    public void actualizar(ArrayList<CartaMonstruo> cartasEnRegionMonstruos)
    {
        // TODO: reemplazar por lambda usando un iterator de cartasEnRegionMonstruo.
        for (int i = 0; i < cartasEnRegionMonstruos.size(); i++)
        {
            botones.get(i).actualizar(cartasEnRegionMonstruos.get(i));
        }
        this.grid.getChildren().clear();
        for (int i = 0; i < this.botones.size(); i++)
        {
            this.grid.add(this.botones.get(i).getBoton(), i, 0);
            this.grid.setHalignment(this.botones.get(i).getBoton(), HPos.CENTER);
        }
    }
}

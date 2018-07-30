package Vista.region;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class RegionesMagicasYTrampasGrid extends GridPane
{
    private static double porcentajeHorizontalDePantalla = 0.082;
    private static double porcentajeVerticalDePantalla = 0.157;
    private final int cantidadBotonesGrid = 5;
    private GridPane grid;
    private ArrayList<RegionesMagicasYTrampasBoton> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMagicasYTrampasGrid(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;

        this.vista = vista;

        this.grid = new GridPane();

        this.botones = new ArrayList<RegionesMagicasYTrampasBoton>();

        ColumnConstraints columna0 = new ColumnConstraints(this.vista.getResolucionHorizontal()*porcentajeHorizontalDePantalla);
        ColumnConstraints columna1 = new ColumnConstraints(this.vista.getResolucionHorizontal()*porcentajeHorizontalDePantalla);
        ColumnConstraints columna2 = new ColumnConstraints(this.vista.getResolucionHorizontal()*porcentajeHorizontalDePantalla);
        ColumnConstraints columna3 = new ColumnConstraints(this.vista.getResolucionHorizontal()*porcentajeHorizontalDePantalla);
        ColumnConstraints columna4 = new ColumnConstraints(this.vista.getResolucionHorizontal()*porcentajeHorizontalDePantalla);

        RowConstraints fila1 = new RowConstraints((this.vista.getResolucionHorizontal()*porcentajeVerticalDePantalla));

        this.grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        this.grid.getRowConstraints().addAll(fila1);

        this.grid.setAlignment(Pos.CENTER);

        RegionesMagicasYTrampasBoton boton;
        for (int i = 0; i < cantidadBotonesGrid; i++)
        {
            boton = new RegionesMagicasYTrampasBoton(this.vista, this.jugadorAsociado);
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
        // TODO: Ver como hacer mas lindo esto
        this.botones = new ArrayList<>();
        RegionesMagicasYTrampasBoton boton;
        for (int i = 0; i < cantidadBotonesGrid; i++)
        {
            boton = new RegionesMagicasYTrampasBoton(this.vista, this.jugadorAsociado);
            this.botones.add(boton);
            // Se posicionan los botones en la grilla de la región monstruo.
            this.grid.add(boton.getBoton(), i, 0);
            this.grid.setHalignment(boton.getBoton(), HPos.CENTER);
        }
    }

    public void actualizarRegion(ArrayList<Carta> cartasEnRegionMagicasYTrampas)
    {
        // TODO: reemplazar por lambda usando un iterator de cartasEnRegionMonstruo.
        for (int i = 0; i < cartasEnRegionMagicasYTrampas.size(); i++)
        {
            botones.get(i).actualizar(cartasEnRegionMagicasYTrampas.get(i));
        }
        this.grid.getChildren().clear();
        for (int i = 0; i < this.botones.size(); i++)
        {
            this.grid.add(this.botones.get(i).getBoton(), i, 0);
            this.grid.setHalignment(this.botones.get(i).getBoton(), HPos.CENTER);
        }
    }
}

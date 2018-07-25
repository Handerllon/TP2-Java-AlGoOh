package Vista.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Vista.Botones.BotonMagicasYTrampasEnRegion;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Screen;

import java.util.ArrayList;

public class GridDeRegionMagicasYTrampas extends GridPane
{
    public static double anchoColumna;
    public static double relacionAnchoColumnaPantalla = 7.25;
    private GridPane grid;
    private ArrayList<BotonMagicasYTrampasEnRegion> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    public GridDeRegionMagicasYTrampas(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;

        this.vista = vista;

        this.grid = new GridPane();

        this.botones = new ArrayList<BotonMagicasYTrampasEnRegion>();

        anchoColumna = Screen.getPrimary().getVisualBounds().getWidth() / relacionAnchoColumnaPantalla;
        ColumnConstraints columna0 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna1 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna2 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna3 = new ColumnConstraints(anchoColumna);
        ColumnConstraints columna4 = new ColumnConstraints(anchoColumna);
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

            BotonMagicasYTrampasEnRegion boton = new BotonMagicasYTrampasEnRegion(this.vista, this.jugadorAsociado);

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

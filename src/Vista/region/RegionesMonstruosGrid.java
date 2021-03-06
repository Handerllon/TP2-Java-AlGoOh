package Vista.region;

import Controlador.observadores.ObservadorDeControlador;
import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class RegionesMonstruosGrid extends GridPane implements ObservadorDeModelo, ObservadorDeControlador
{
    // Se uso como base una resolucion de 1920x1080
    private static double porcentajeHorizontalDePantalla = 0.082;
    private static double porcentajeVerticalDePantalla = 0.157;
    private static String locacionFondo = "resources/imagenes/RegionMonstruosFondo.jpg";
    private int cantidadBotonesGrid = 5;
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

        vista.getModelo().registrarObsevador(this);
        this.vista.getControlador().registrarObsevador(this);

        this.grid = new GridPane();

        ColumnConstraints columna0 = new ColumnConstraints(this.vista.getResolucionHorizontal() * porcentajeHorizontalDePantalla);
        ColumnConstraints columna1 = new ColumnConstraints(this.vista.getResolucionHorizontal() * porcentajeHorizontalDePantalla);
        ColumnConstraints columna2 = new ColumnConstraints(this.vista.getResolucionHorizontal() * porcentajeHorizontalDePantalla);
        ColumnConstraints columna3 = new ColumnConstraints(this.vista.getResolucionHorizontal() * porcentajeHorizontalDePantalla);
        ColumnConstraints columna4 = new ColumnConstraints(this.vista.getResolucionHorizontal() * porcentajeHorizontalDePantalla);

        RowConstraints fila1 = new RowConstraints((this.vista.getResolucionHorizontal() * porcentajeVerticalDePantalla));

        this.grid.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        this.grid.getRowConstraints().addAll(fila1);

        this.grid.setAlignment(Pos.CENTER);

        this.actualizarRegion();
    }

    public void actualizarRegion()
    {

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

        // Se obtienen las cartas en la región monstruos del jugador y se le asocian los botones.
        ArrayList<CartaMonstruo> cartasEnRegionMonstruos =
                this.vista.getModelo().getCartasEnRegionMonstruosDe(this.jugadorAsociado);

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

        this.vista.actualizarDibujo();
    }

    public GridPane getGrid()
    {
        return this.grid;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void seTomoCartaDeMazo()
    {

    }

    @Override
    public void ingresoCartaAMano()
    {

    }

    @Override
    public void egresoCartaAMano()
    {

    }

    @Override
    public void ingresoCartaARegion()
    {
        actualizarRegion();
    }

    @Override
    public void egresoCartaARegion()
    {
        actualizarRegion();
    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {
        actualizarRegion();
    }

    @Override
    public void cartaCambioDeModo()
    {
        actualizarRegion();
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Controlador.
    // --------------------------------------------------------------------
    @Override
    public void seTerminoElTurno()
    {

    }

    @Override
    public void seTerminoLaFase()
    {
        actualizarRegion();
    }
}

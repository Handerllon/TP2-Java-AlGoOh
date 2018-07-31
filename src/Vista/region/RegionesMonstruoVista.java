package Vista.region;

import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionesMonstruoVista
{
    private Vista vista;
    private RegionesMonstruosGrid gridJugador;
    private RegionesMonstruosGrid gridOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruoVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador = new RegionesMonstruosGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new RegionesMonstruosGrid(this.vista, this.vista.getModelo().getOponente());
    }

    public GridPane getGridJugador()
    {

        return gridJugador.getGrid();
    }

    public GridPane getGridOponente()
    {

        return gridOponente.getGrid();
    }
}

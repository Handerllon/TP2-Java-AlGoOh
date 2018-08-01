package Vista.region;

import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionesMonstruoVista
{
    private Vista vista;
    private RegionesMonstruosGrid gridJugador1, gridJugador2;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMonstruoVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador1 = new RegionesMonstruosGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridJugador2 = new RegionesMonstruosGrid(this.vista, this.vista.getModelo().getOponente());
    }

    public GridPane getGridJugador1()
    {

        return gridJugador1.getGrid();
    }

    public GridPane getGridJugador2()
    {

        return gridJugador2.getGrid();
    }
}

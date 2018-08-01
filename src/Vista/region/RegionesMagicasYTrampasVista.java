package Vista.region;

import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionesMagicasYTrampasVista
{
    private Vista vista;
    private RegionesMagicasYTrampasGrid gridJugador1, gridJugador2;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMagicasYTrampasVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador1 = new RegionesMagicasYTrampasGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridJugador2 = new RegionesMagicasYTrampasGrid(this.vista, this.vista.getModelo().getOponente());
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

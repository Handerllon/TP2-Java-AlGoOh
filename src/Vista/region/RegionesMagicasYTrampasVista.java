package Vista.region;

import Vista.Vista;
import javafx.scene.layout.GridPane;

public class RegionesMagicasYTrampasVista
{
    private Vista vista;
    private RegionesMagicasYTrampasGrid gridJugador;
    private RegionesMagicasYTrampasGrid gridOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesMagicasYTrampasVista(Vista vista)
    {
        this.vista = vista;

        this.gridJugador = new RegionesMagicasYTrampasGrid(this.vista, this.vista.getModelo().getJugador());
        this.gridOponente = new RegionesMagicasYTrampasGrid(this.vista, this.vista.getModelo().getOponente());
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

package Vista.region;

import Vista.Vista;
import javafx.scene.control.Button;

public class RegionesCampoVista
{
    private Vista vista;
    private RegionesCampoBoton botonCampoJugador;
    private RegionesCampoBoton botonCampoOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCampoVista(Vista vista)
    {
        this.vista = vista;

        this.botonCampoJugador = new RegionesCampoBoton(this.vista, this.vista.getModelo().getJugador());
        this.botonCampoOponente = new RegionesCampoBoton(this.vista, this.vista.getModelo().getOponente());
    }

    public Button getRegionCampoJugador()
    {

        return botonCampoJugador.getBoton();
    }

    public Button getRegionCampoOponente()
    {

        return botonCampoOponente.getBoton();
    }
}

package Vista.region;

import Vista.Vista;
import javafx.scene.control.Button;

public class RegionesCampoVista
{
    private Vista vista;
    private RegionesCampoBoton botonCampoJugador1, botonCampoJugador2;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCampoVista(Vista vista)
    {
        this.vista = vista;

        this.botonCampoJugador1 = new RegionesCampoBoton(this.vista, this.vista.getModelo().getJugador());
        this.botonCampoJugador2 = new RegionesCampoBoton(this.vista, this.vista.getModelo().getOponente());
    }

    public Button getRegionCampoJugador1()
    {

        return botonCampoJugador1.getBotonCarta();
    }

    public Button getRegionCampoJugador2()
    {

        return botonCampoJugador2.getBotonCarta();
    }
}

package Vista.region;

import Modelo.carta.campo.CartaCampo;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class RegionCampoVista implements ObservadorDeModelo
{
    private Vista vista;
    private RegionCampoBoton botonCampoJugador;
    private RegionCampoBoton botonCampoOponente;

    public RegionCampoVista(Vista vista)
    {
        this.vista = vista;

        this.botonCampoJugador = new RegionCampoBoton(this.vista, this.vista.getModelo().getJugador());
        this.botonCampoOponente = new RegionCampoBoton(this.vista, this.vista.getModelo().getOponente());
    }

    public Button getRegionCampoJugador()
    {

        return botonCampoJugador.getBoton();
    }

    public Button getRegionCampoOponente()
    {

        return botonCampoOponente.getBoton();
    }

    @Override
    public void huboCambios()
    {
        this.botonCampoJugador.clear();
        this.botonCampoOponente.clear();
        this.actualizarRegionJugador(this.vista.getModelo().getCartasEnRegionCampoJugador());
        this.actualizarRegionOponente(this.vista.getModelo().getCartasEnRegionCampoOponente());
    }

    private void actualizarRegionJugador(ArrayList<CartaCampo> cartasCampo)
    {
        if (!cartasCampo.isEmpty())
        {
            this.botonCampoJugador.actualizarImagen(cartasCampo.iterator().next());
        } else
        {

        }
    }

    private void actualizarRegionOponente(ArrayList<CartaCampo> cartasCampo)
    {
        if (!cartasCampo.isEmpty())
        {
            this.botonCampoOponente.actualizarImagen(cartasCampo.iterator().next());
        } else
        {

        }
    }
}

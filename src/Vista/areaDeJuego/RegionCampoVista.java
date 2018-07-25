package Vista.areaDeJuego;

import Modelo.ObservadorDeModelo;
import Modelo.carta.campo.CartaCampo;
import Vista.Botones.BotonCampoEnRegion;
import Vista.Vista;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class RegionCampoVista implements ObservadorDeModelo
{
    private Vista vista;
    private BotonCampoEnRegion botonCampoJugador;
    private BotonCampoEnRegion botonCampoOponente;

    public RegionCampoVista(Vista vista)
    {
        this.vista = vista;

        this.botonCampoJugador = new BotonCampoEnRegion(this.vista, this.vista.getModelo().getJugador());
        this.botonCampoOponente = new BotonCampoEnRegion(this.vista, this.vista.getModelo().getOponente());
    }

    public Button getRegionCampoJugador()
    {

        return botonCampoJugador.obtenerBoton();
    }

    public Button getRegionCampoOponente()
    {

        return botonCampoOponente.obtenerBoton();
    }

    @Override
    public void actualizarEstado()
    {
        this.botonCampoJugador.clear();
        this.botonCampoOponente.clear();
        this.actualizarRegionJugador(this.vista.getModelo().getCartasEnRegionCampoJugador());
        this.actualizarRegionOponente(this.vista.getModelo().getCartasEnRegionCampoOponente());
    }

    private void actualizarRegionJugador(ArrayList<CartaCampo> cartasCampo)
    {
        this.botonCampoJugador.actualizarImagen(cartasCampo.get(0));
    }

    private void actualizarRegionOponente(ArrayList<CartaCampo> cartasCampo)
    {
        this.botonCampoOponente.actualizarImagen(cartasCampo.get(0));
    }
}

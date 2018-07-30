package Vista.region;

import Modelo.carta.campo.CartaCampo;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class RegionesCampoVista implements ObservadorDeModelo
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
        // TODO: Para cuando se implemente los observadores puntuales:
        // vista.getModelo().registrarObsevador(this);

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

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
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

    @Override
    public void seTomoCartaDeMazo()
    {

    }
}

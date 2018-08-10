package Controlador.visitor;

import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Vista.Vista;

public class VisitadorSolicitudCartaAtacar implements VisitadorCarta
{
    private Vista vista;

    public VisitadorSolicitudCartaAtacar(Vista vista)
    {
        this.vista = vista;
    }

    @Override
    public void visitarCartaMonstruo(CartaMonstruo carta)
    {
        this.vista.solicitarCartaAAtacar(carta);
    }

    @Override
    public void visitarCartaMagica(CartaMagica carta)
    {

    }

    @Override
    public void visitarCartaTrampa(CartaTrampa carta)
    {

    }

    @Override
    public void visitarCartaCampo(CartaCampo carta)
    {

    }
}

package Controlador.visitor;

import Modelo.Modelo;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;

public class VisitadorModoCarta implements VisitadorCarta
{
    private final Modelo modelo;

    public VisitadorModoCarta(Modelo modelo)
    {
        this.modelo = modelo;
    }

    @Override
    public void visitarCartaMonstruo(CartaMonstruo carta)
    {
        this.modelo.cambiarModoCartaMonstruo(carta);
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

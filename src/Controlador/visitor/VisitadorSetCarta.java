package Controlador.visitor;

import Modelo.Modelo;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;

public class VisitadorSetCarta implements VisitadorCarta
{
    private final Modelo modelo;

    public VisitadorSetCarta(Modelo modelo)
    {
        this.modelo = modelo;
    }

    @Override
    public void visitarCartaMonstruo(CartaMonstruo carta)
    {
        this.modelo.setCartaMonstruo(carta);
    }

    @Override
    public void visitarCartaMagica(CartaMagica carta)
    {
        this.modelo.setCartaMagica(carta);
    }

    @Override
    public void visitarCartaTrampa(CartaTrampa carta)
    {
        this.modelo.setCartaTrampa(carta);
    }

    @Override
    public void visitarCartaCampo(CartaCampo carta)
    {

    }
}

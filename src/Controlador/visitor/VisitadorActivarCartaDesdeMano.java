package Controlador.visitor;

import Modelo.Modelo;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;

public class VisitadorActivarCartaDesdeMano implements VisitadorCarta
{
    private final Modelo modelo;

    public VisitadorActivarCartaDesdeMano(Modelo modelo)
    {
        this.modelo = modelo;
    }

    @Override
    public void visitarCartaMonstruo(CartaMonstruo carta)
    {

    }

    @Override
    public void visitarCartaMagica(CartaMagica carta)
    {
        this.modelo.activarCartaMagicaDesdeMano(carta);
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

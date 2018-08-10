package Vista.visitador;

import Controlador.visitor.VisitadorCarta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Vista.region.RegionesMagicasYTrampasBoton;

public class VisitadorBotonRegionMagicasYTrampas implements VisitadorCarta
{
    RegionesMagicasYTrampasBoton region;

    public VisitadorBotonRegionMagicasYTrampas(RegionesMagicasYTrampasBoton regionesMagicasYTrampasBoton)
    {
        this.region = regionesMagicasYTrampasBoton;
    }

    @Override
    public void visitarCartaMonstruo(CartaMonstruo carta)
    {

    }

    @Override
    public void visitarCartaMagica(CartaMagica carta)
    {
        this.region.setBotonActivarCartaMagica();
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

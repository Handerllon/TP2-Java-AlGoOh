package Vista.visitador;

import Controlador.visitor.VisitadorCarta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Vista.carta.mano.ManoBoton;

public class VisitadorBotonCarta implements VisitadorCarta
{
    ManoBoton boton;

    public VisitadorBotonCarta(ManoBoton manoBoton)
    {
        this.boton = manoBoton;
    }

    @Override
    public void visitarCartaMonstruo(CartaMonstruo carta)
    {

        this.boton.crearBotonMonstruo();
    }

    @Override
    public void visitarCartaMagica(CartaMagica carta)
    {
        this.boton.crearBotonCartaMagica();
    }

    @Override
    public void visitarCartaTrampa(CartaTrampa carta)
    {
        this.boton.crearBotonCartaTrampa();
    }

    @Override
    public void visitarCartaCampo(CartaCampo carta)
    {
        this.boton.crearBotonCartaCampo();
    }
}

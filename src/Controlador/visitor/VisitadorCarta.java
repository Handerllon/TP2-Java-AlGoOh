package Controlador.visitor;

import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;

public interface VisitadorCarta
{
    void visitarCartaMonstruo(CartaMonstruo carta);

    void visitarCartaMagica(CartaMagica carta);

    void visitarCartaTrampa(CartaTrampa carta);

    void visitarCartaCampo(CartaCampo carta);
}

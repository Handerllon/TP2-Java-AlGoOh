package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class CharcoalInpachi extends CartaMonstruo
{
    public CharcoalInpachi()
    {
        this.puntosAtaque = 100;
        this.puntosDefensa = 2100;
        this.estrellas = 1;
        this.nombre = "Charcoal Inpachi";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

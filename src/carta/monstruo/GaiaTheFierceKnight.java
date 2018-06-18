package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class GaiaTheFierceKnight extends CartaMonstruo
{
    public GaiaTheFierceKnight()
    {
        this.puntosAtaque = 2300;
        this.puntosDefensa = 2100;
        this.estrellas = 7;
        this.nombre = "Gaia The Fierce Knight";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

package carta.monstruo;

import carta.ModoDefensa;

public class GaiaTheFierceKnight extends CartaMonstruo
{
    public GaiaTheFierceKnight()
    {
        this.puntosAtaque = 2300;
        this.puntosDefensa = 2100;
        this.nivel = 7;
        this.nombre = "Gaia The Fierce Knight";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
}

package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class AncientBrain extends CartaMonstruo
{
    public AncientBrain()
    {
        this.puntosAtaque = 1000;
        this.puntosDefensa = 700;
        this.estrellas = 3;
        this.nombre = "Ancient Brain";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

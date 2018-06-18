package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class RightArmOfTheForbiddenOne extends CartaMonstruo
{
	public RightArmOfTheForbiddenOne()
    {
        this.puntosAtaque = 200;
        this.puntosDefensa = 300;
        this.estrellas = 1;
        this.nombre = "Right Arm Of The Forbidden One";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

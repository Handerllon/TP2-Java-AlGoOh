package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class LeftArmOfTheForbiddenOne extends CartaMonstruo
{
	public LeftArmOfTheForbiddenOne()
    {
        this.puntosAtaque = 200;
        this.puntosDefensa = 300;
        this.estrellas = 1;
        this.nombre = "Left Arm Of The Forbidden One";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

package carta.monstruo;

import carta.ModoDefensa;

public class RightLegOfTheForbiddenOne extends CartaMonstruo
{
	public RightLegOfTheForbiddenOne()
    {
        this.puntosAtaque = 200;
        this.puntosDefensa = 300;
        this.estrellas = 1;
        this.nombre = "Right Leg Of The Forbidden One";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
}

package carta.monstruo;

import carta.ModoDefensa;

public class LeftLegOfTheForbiddenOne extends CartaMonstruo
{
	public LeftLegOfTheForbiddenOne()
    {
        this.puntosAtaque = 200;
        this.puntosDefensa = 300;
        this.estrellas = 1;
        this.nombre = "Left Leg Of The Forbidden One";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
}

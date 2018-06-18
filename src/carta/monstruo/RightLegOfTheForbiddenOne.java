package carta.monstruo;

import carta.CartaMonstruo;

public class RightLegOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public RightLegOfTheForbiddenOne()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 1;
        this.nombre = "Right Leg Of The Forbidden One";
    }
}

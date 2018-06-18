package carta.monstruo;

import carta.CartaMonstruo;

public class RightArmOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public RightArmOfTheForbiddenOne()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 1;
        this.nombre = "Right Arm Of The Forbidden One";
    }
}

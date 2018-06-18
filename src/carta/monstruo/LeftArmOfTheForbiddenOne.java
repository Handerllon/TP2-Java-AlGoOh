package carta.monstruo;

import carta.CartaMonstruo;

public class LeftArmOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public LeftArmOfTheForbiddenOne()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 1;
        this.nombre = "Left Arm Of The Forbidden One";
    }
}

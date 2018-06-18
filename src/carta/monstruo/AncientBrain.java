package carta.monstruo;

import carta.CartaMonstruo;

public class AncientBrain extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 700;
    private static int PUNTOS_ATAQUE = 1000;

    public AncientBrain()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 3;
        this.nombre = "Ancient Brain";
    }
}

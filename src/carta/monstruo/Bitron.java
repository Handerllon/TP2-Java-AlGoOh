package carta.monstruo;

import carta.CartaMonstruo;

public class Bitron extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 200;

    public Bitron()
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 2;
        this.nombre = "Bitron";
    }
}

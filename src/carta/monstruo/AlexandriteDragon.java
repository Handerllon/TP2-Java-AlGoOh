package carta.monstruo;

import carta.CartaMonstruo;

public class AlexandriteDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 100;
    private static int PUNTOS_ATAQUE = 2000;

    public AlexandriteDragon()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 4;
        this.nombre = "Alexandrite Dragon";
    }
}

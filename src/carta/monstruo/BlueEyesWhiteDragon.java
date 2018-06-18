package carta.monstruo;

import carta.CartaMonstruo;

public class BlueEyesWhiteDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2500;
    private static int PUNTOS_ATAQUE = 3000;

    public BlueEyesWhiteDragon()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 8;
        this.nombre = "Blue Eyes White Dragon";
    }
}

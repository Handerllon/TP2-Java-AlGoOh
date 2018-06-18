package carta.monstruo;

import carta.CartaMonstruo;

public class BlueEyesUltimateDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 3800;
    private static int PUNTOS_ATAQUE = 4500;

    public BlueEyesUltimateDragon()
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 12;
        this.nombre = "Blue Eyes Ultimate Dragon";
    }
}

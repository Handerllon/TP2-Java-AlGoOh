package carta.monstruo;

import carta.CartaMonstruo;

public class AncientTool extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 1400;
    private static int PUNTOS_ATAQUE = 1700;

    public AncientTool()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 5;
        this.nombre = "AncientTool";
    }
}

package carta.monstruo;

import carta.CartaMonstruo;

public class AmphibianBeast extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 2400;

    public AmphibianBeast()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 6;
        this.nombre = "Amphibian Beast";
    }
}

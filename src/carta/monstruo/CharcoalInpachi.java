package carta.monstruo;

import carta.CartaMonstruo;

public class CharcoalInpachi extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2100;
    private static int PUNTOS_ATAQUE = 100;

    public CharcoalInpachi()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 1;
        this.nombre = "Charcoal Inpachi";
    }
}

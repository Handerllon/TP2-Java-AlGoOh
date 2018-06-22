package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class AmphibianBeast extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 2400;

    public AmphibianBeast(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE,jugador,oponente);
        this.estrellas = 6;
        this.nombre = "Amphibian Beast";
    }
}

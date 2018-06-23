package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;

public class AlexandriteDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 100;
    private static int PUNTOS_ATAQUE = 2000;

    public AlexandriteDragon(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE,jugador,oponente);
        this.estrellas = 4;
        this.nombre = "Alexandrite Dragon";
    }
}

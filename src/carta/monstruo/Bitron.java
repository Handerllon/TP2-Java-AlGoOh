package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;

public class Bitron extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 200;

    public Bitron(Jugador jugador, Jugador oponente)
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE,jugador,oponente);
        this.estrellas = 2;
        this.nombre = "Bitron";
    }

}

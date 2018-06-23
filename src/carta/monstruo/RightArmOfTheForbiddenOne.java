package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;

public class RightArmOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public RightArmOfTheForbiddenOne(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE,jugador,oponente);
        this.estrellas = 1;
        this.nombre = "Right Arm Of The Forbidden One";
    }

}

package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class LeftLegOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public LeftLegOfTheForbiddenOne(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 1;
        this.nombre = "Left Leg Of The Forbidden One";
    }
}

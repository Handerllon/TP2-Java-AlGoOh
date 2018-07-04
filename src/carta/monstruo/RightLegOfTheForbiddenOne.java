package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class RightLegOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public RightLegOfTheForbiddenOne(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 1;
        this.nombre = "Right Leg Of The Forbidden One";
    }
}

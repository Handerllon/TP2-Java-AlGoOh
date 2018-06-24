package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class LeftLegOfTheForbiddenOne extends CartaMonstruo {
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public LeftLegOfTheForbiddenOne(Jugador jugador, Jugador oponente) {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente);
        this.estrellas = 1;
        this.nombre = "Left Leg Of The Forbidden One";
    }
}

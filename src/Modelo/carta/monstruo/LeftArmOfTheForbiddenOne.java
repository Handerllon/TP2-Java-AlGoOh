package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class LeftArmOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public LeftArmOfTheForbiddenOne(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 1;
        this.nombre = "Left Arm Of The Forbidden One";
    }

    public boolean esParteExodia()
    {
        return true;
    }
}

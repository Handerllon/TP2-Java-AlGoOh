package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class RightArmOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;

    public RightArmOfTheForbiddenOne(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 1;
        this.nombre = "Right Arm Of The Forbidden One";
    }
}

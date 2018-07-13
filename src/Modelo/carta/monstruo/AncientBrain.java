package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class AncientBrain extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 700;
    private static int PUNTOS_ATAQUE = 1000;

    public AncientBrain(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 3;
        this.nombre = "Ancient Brain";
    }
}

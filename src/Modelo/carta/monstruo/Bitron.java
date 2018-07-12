package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class Bitron extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 200;

    public Bitron(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 2;
        this.nombre = "Bitron";
    }
}

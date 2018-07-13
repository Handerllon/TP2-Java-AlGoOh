package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class CharcoalInpachi extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2100;
    private static int PUNTOS_ATAQUE = 100;

    public CharcoalInpachi(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 1;
        this.nombre = "Charcoal Inpachi";
    }
}

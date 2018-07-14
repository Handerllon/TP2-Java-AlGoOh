package Modelo.carta.monstruo;

import Modelo.Jugador;

public class CharcoalInpachi extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2100;
    private static int PUNTOS_ATAQUE = 100;
    private static String rutaImagen = "resources/imagenes/monstruo/CharcoalInpachi.png";

    public CharcoalInpachi(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 1;
        this.nombre = "Charcoal Inpachi";
    }
}

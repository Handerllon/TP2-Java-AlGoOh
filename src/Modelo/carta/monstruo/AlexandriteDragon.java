package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class AlexandriteDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 100;
    private static int PUNTOS_ATAQUE = 2000;
    private static String rutaImagen = "resources/imagenes/monstruo/AlexandriteDragon.png";

    public AlexandriteDragon(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 4;
        this.nombre = "Alexandrite Dragon";
    }
}

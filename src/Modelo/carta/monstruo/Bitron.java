package Modelo.carta.monstruo;

import Modelo.Jugador;

public class Bitron extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 200;
    private static String rutaImagen = "resources/imagenes/monstruo/Bitron.png";

    public Bitron(Jugador jugador, Jugador oponente)
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 2;
        this.nombre = "Bitron";
    }
}

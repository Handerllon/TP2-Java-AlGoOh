package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class AncientBrain extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 700;
    private static int PUNTOS_ATAQUE = 1000;
    private static String rutaImagen = "resources/imagenes/monstruo/AncientBrain.png";

    public AncientBrain(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 3;
        this.nombre = "Ancient Brain";
    }
}

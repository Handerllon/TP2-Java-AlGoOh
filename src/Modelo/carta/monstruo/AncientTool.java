package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.Sacrificio;

public class AncientTool extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 1400;
    private static int PUNTOS_ATAQUE = 1700;
    private static String rutaImagen = "resources/imagenes/monstruo/AncientTool.png";

    public AncientTool(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 5;
        this.nombre = "Ancient Tool";
    }

    public void invocar(Sacrificio sacrificio)
    {
        this.jugador.destruirMonstruo(sacrificio.getMonstruo());
        this.jugador.setCarta(this);
    }
}

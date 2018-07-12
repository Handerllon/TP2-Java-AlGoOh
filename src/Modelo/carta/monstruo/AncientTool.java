package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;
import Modelo.carta.Sacrificio;

public class AncientTool extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 1400;
    private static int PUNTOS_ATAQUE = 1700;

    public AncientTool(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 5;
        this.nombre = "Ancient Tool";
    }

    public void invocar(Sacrificio sacrificio)
    {
        this.jugador.destruirMonstruo(sacrificio.getMonstruo());
        this.jugador.jugarCarta(this);
    }
}

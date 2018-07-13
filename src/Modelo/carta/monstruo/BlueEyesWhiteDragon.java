package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;
import Modelo.carta.Sacrificio;

public class BlueEyesWhiteDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2500;
    private static int PUNTOS_ATAQUE = 3000;

    public BlueEyesWhiteDragon(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 8;
        this.nombre = "Blue-Eyes White Dragon";
    }

    public void invocar(Sacrificio sacrificio)
    {
        this.jugador.destruirMonstruo(sacrificio.getMonstruo());
        this.jugador.destruirMonstruo(sacrificio.getMonstruo());

        this.jugador.jugarCarta(this);
    }
}

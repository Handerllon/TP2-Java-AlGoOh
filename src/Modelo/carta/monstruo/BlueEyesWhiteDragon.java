package Modelo.carta.monstruo;

import Modelo.Jugador;

public class BlueEyesWhiteDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2500;
    private static int PUNTOS_ATAQUE = 3000;
    private static String rutaImagen = "resources/imagenes/monstruo/BlueEyesWhiteDragon.png";

    public BlueEyesWhiteDragon(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 8;
        this.nombre = "Blue-Eyes White Dragon";
    }

    @Override
    public boolean requiereSacrificios()
    {
        return true;
    }

    @Override
    public int getCantidadSacrificiosRequeridos()
    {
        return 2;
    }
}

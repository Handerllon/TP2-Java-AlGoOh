package Modelo.carta.monstruo;

import Modelo.Jugador;

public class GaiaTheFierceKnight extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2100;
    private static int PUNTOS_ATAQUE = 2300;
    private static String rutaImagen = "resources/imagenes/monstruo/GaiaTheFierceKnight.png";

    public GaiaTheFierceKnight(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 7;
        this.nombre = "Gaia The Fierce Knight";
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

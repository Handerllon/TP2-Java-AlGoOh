package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;
import Modelo.carta.Sacrificio;

public class AmphibianBeast extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2000;
    private static int PUNTOS_ATAQUE = 2400;
    private static String rutaImagen = "resources/imagenes/monstruo/AmphibianBeast.jpg";

    public AmphibianBeast(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 6;
        this.nombre = "Amphibian Beast";
    }

    public void invocar(Sacrificio sacrificio)
    {
        this.jugador.destruirMonstruo(sacrificio.getMonstruo());

        this.jugador.jugarCarta(this);
    }
}

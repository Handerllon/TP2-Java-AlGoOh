package Modelo.carta.monstruo;

import Modelo.Jugador;
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

    public void summon(Sacrificio sacrificio)
    {
        this.jugador.destruirCarta(sacrificio.getMonstruo());

        this.jugador.enviarARegion(this);
    }

    @Override
    public boolean requiereSacrificio()
    {
        return true;
    }
}

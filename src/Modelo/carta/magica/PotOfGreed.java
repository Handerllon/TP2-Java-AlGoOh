package Modelo.carta.magica;

import Modelo.Jugador;

public class PotOfGreed extends CartaMagica
{
    private static String rutaImagen = "resources/imagenes/magica/PotOfGreed.png";

    public PotOfGreed(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Pot of Greed";
    }

    public void efecto()
    {
        this.jugador.tomarCarta();
        this.jugador.tomarCarta();
    }
}

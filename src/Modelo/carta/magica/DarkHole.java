package Modelo.carta.magica;

import Modelo.Jugador;

public class DarkHole extends CartaMagica
{
    private static String rutaImagen = "resources/imagenes/magica/DarkHole.png";

    public DarkHole(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Dark Hole";
    }

    public void efecto()
    {
        if (this.estaBocaArriba() == true)
        {
            this.jugador.destruirMonstruos();
            this.oponente.destruirMonstruos();
        }
    }
}

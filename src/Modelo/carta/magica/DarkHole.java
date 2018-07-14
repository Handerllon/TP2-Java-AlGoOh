package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.CartaMagica;

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
        if (this.orientacionArriba())
        {
            this.jugador.destruirMonstruos();
            this.oponente.destruirMonstruos();
        }
    }

    public void cambiarOrientacion()
    {
        this.orientacion.cambiarOrientacion(this);
    }
}

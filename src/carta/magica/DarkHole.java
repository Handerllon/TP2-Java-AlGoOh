package carta.magica;

import AlGoOh.Jugador;
import carta.CartaMagica;

public class DarkHole extends CartaMagica
{
    public DarkHole(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(jugador, oponente,locacionDeImagen);
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

package carta.magica;

import AlGoOh.Jugador;
import carta.CartaMagica;
import carta.EfectoNulo;

public class DarkHole extends CartaMagica
{

    public DarkHole()
    {
        super();
        this.nombre = "Dark Hole";
    }

    public void efecto(Jugador jugador, Jugador oponente)
    {
        this.efecto.efecto(jugador,oponente);
    }

    public void cambiarOrientacion()
    {
        this.orientacion.cambiarOrientacion(this);

        if (this.orientacionArriba())
        {
            this.efecto = new DarkHoleEfecto();
        } else
        {
            this.efecto = new EfectoNulo();
        }
    }
}

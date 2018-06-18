package carta.magica;

import areaDeJuego.Tablero;
import carta.CartaMagica;
import carta.EfectoNulo;

public class DarkHole extends CartaMagica
{

    public DarkHole()
    {
        super();
        this.nombre = "Dark Hole";
    }

    public void efecto(Tablero tablero)
    {
        this.efecto.efecto(tablero);
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

package carta.magica;

import areaDeJuego.Tablero;
import carta.CartaMagica;
import carta.EfectoNulo;
import carta.OrientacionAbajo;

public class DarkHole extends CartaMagica
{

    public DarkHole()
    {
        this.nombre = "Dark Hole";
        this.orientacion = new OrientacionAbajo();
        this.efecto = new EfectoNulo();
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

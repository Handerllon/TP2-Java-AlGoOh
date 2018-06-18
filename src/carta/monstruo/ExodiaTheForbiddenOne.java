package carta.monstruo;

import areaDeJuego.Tablero;
import carta.Efecto;
import carta.ModoDefensa;

public class ExodiaTheForbiddenOne extends CartaMonstruo implements Efecto
{
	
	public ExodiaTheForbiddenOne()
    {
        this.puntosAtaque = 1000;
        this.puntosDefensa = 1000;
        this.nivel = 3;
        this.nombre = "Exodia The Forbidden One";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
	
    public void efecto(Tablero tablero)
    {
    }
}

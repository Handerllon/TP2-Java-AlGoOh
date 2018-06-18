package carta.monstruo;

import areaDeJuego.Tablero;
import carta.CartaMonstruo;
import carta.Efecto;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class ExodiaTheForbiddenOne extends CartaMonstruo implements Efecto
{
	
	public ExodiaTheForbiddenOne()
    {
        this.puntosAtaque = 1000;
        this.puntosDefensa = 1000;
        this.estrellas = 3;
        this.nombre = "Exodia The Forbidden One";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
	
    public void efecto(Tablero tablero)
    {
    }
}

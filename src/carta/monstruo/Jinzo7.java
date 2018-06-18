package carta.monstruo;

import areaDeJuego.Tablero;
import carta.CartaMonstruo;
import carta.Efecto;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class Jinzo7 extends CartaMonstruo implements Efecto
{
	public Jinzo7()
    {
        this.puntosAtaque = 500;
        this.puntosDefensa = 400;
        this.estrellas = 2;
        this.nombre = "Jinzo7";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
	
    public void efecto(Tablero tablero)
    {
    }
}

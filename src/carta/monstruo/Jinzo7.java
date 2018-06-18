package carta.monstruo;

import areaDeJuego.Tablero;
import carta.Efecto;
import carta.ModoDefensa;

public class Jinzo7 extends CartaMonstruo implements Efecto
{
	public Jinzo7()
    {
        this.puntosAtaque = 500;
        this.puntosDefensa = 400;
        this.nivel = 2;
        this.nombre = "Jinzo7";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
	
    public void efecto(Tablero tablero)
    {
    }
}

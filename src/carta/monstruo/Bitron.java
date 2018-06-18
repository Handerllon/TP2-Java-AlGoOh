package carta.monstruo;

import carta.Carta;
import carta.ModoDefensa;

public class Bitron extends CartaMonstruo
{
	public Bitron(){

        this.puntosAtaque = 200;
        this.puntosDefensa = 2000;
        this.nivel = 2;
        this.nombre = "Bitron";

        this.modo = new ModoDefensa(this.puntosDefensa);
	}
}

package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class BlueEyesWhiteDragon extends CartaMonstruo
{
	public BlueEyesWhiteDragon(){

        this.puntosAtaque = 3000;
        this.puntosDefensa = 2500;
        this.estrellas = 8;
        this.nombre = "Blue Eyes White Dragon";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
	}
}

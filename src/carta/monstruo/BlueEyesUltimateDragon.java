package carta.monstruo;

import carta.ModoDefensa;

public class BlueEyesUltimateDragon extends CartaMonstruo
{
	public BlueEyesUltimateDragon(){

        this.puntosAtaque = 4500;
        this.puntosDefensa = 3800;
        this.estrellas = 12;
        this.nombre = "Blue Eyes Ultimate Dragon";

        this.modo = new ModoDefensa(this.puntosDefensa);
	}
}

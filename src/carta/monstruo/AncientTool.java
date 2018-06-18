package carta.monstruo;

import carta.ModoDefensa;

public class AncientTool extends CartaMonstruo
{
	public AncientTool(){

        this.puntosAtaque = 1700;
        this.puntosDefensa = 1400;
        this.estrellas = 5;
        this.nombre = "AncientTool";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
}

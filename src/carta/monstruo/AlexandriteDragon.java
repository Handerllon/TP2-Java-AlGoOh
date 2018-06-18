package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class AlexandriteDragon extends CartaMonstruo
{
    public AlexandriteDragon(){

        this.puntosAtaque = 2000;
        this.puntosDefensa = 100;
        this.estrellas = 4;
        this.nombre = "Alexandrite Dragon";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

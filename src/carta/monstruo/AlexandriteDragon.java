package carta.monstruo;

import carta.ModoDefensa;

public class AlexandriteDragon extends CartaMonstruo
{
    public AlexandriteDragon(){

        this.puntosAtaque = 2000;
        this.puntosDefensa = 100;
        this.estrellas = 4;
        this.nombre = "Alexandrite Dragon";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
}

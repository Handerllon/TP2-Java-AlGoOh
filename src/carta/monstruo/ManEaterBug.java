package carta.monstruo;

import areaDeJuego.Tablero;
import carta.Efecto;
import carta.ModoDefensa;

public class ManEaterBug extends CartaMonstruo implements Efecto
{

    public ManEaterBug()
    {
        this.puntosAtaque = 450;
        this.puntosDefensa = 600;
        this.estrellas = 2;
        this.nombre = "Man Eater Bug";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }

    public void efecto(Tablero tablero)
    {
    }
}

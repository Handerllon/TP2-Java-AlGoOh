package carta.monstruo;

import areaDeJuego.Tablero;
import carta.CartaMonstruo;
import carta.Efecto;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class ManEaterBug extends CartaMonstruo implements Efecto
{

    public ManEaterBug()
    {
        this.puntosAtaque = 450;
        this.puntosDefensa = 600;
        this.estrellas = 2;
        this.nombre = "Man Eater Bug";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }

    public void efecto(Tablero tablero)
    {
    }
}

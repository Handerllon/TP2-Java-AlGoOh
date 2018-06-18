package carta.monstruo;

import carta.CartaMonstruo;
import carta.ModoDefensa;
import carta.OrientacionAbajo;

public class AmphibianBeast extends CartaMonstruo
{
    public AmphibianBeast()
    {
        this.puntosAtaque = 2400;
        this.puntosDefensa = 2000;
        this.estrellas = 6;
        this.nombre = "Amphibian Beast";

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
        this.orientacion = new OrientacionAbajo();
    }
}

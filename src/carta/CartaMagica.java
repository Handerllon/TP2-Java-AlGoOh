package carta;

import carta.Carta;
import carta.Efecto;
import carta.OrientacionAbajo;

public abstract class CartaMagica extends Carta implements Efecto
{
    protected Efecto efecto;

    public CartaMagica()
    {
        this.orientacion = new OrientacionAbajo();
    }
}

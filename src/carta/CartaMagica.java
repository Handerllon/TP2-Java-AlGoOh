package carta;

public abstract class CartaMagica extends Carta implements Efecto
{
    protected Efecto efecto;

    public CartaMagica()
    {
        this.orientacion = new OrientacionAbajo();
    }
}

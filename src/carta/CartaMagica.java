package carta;

public abstract class CartaMagica extends Carta implements Efecto
{
    protected Efecto efecto;

    public CartaMagica(){
        super();
        this.efecto = new EfectoNulo();
    }
}

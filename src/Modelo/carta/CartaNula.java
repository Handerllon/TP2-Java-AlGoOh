package Modelo.carta;

public final class CartaNula extends Carta
{
    private static CartaNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private CartaNula()
    {
        super(null, null, null);
    }

    public static CartaNula obtenerInstancia()
    {
        if (instancia == null)
        {
            instancia = new CartaNula();
        }
        return instancia;
    }

    public CartaNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
}

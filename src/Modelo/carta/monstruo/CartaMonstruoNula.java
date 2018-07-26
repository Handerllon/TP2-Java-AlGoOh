package Modelo.carta.monstruo;

public final class CartaMonstruoNula extends CartaMonstruo
{
    private static CartaMonstruoNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private CartaMonstruoNula()
    {
        super(0, 0, null, null, null);
    }

    public static CartaMonstruoNula getInstancia()
    {
        if (instancia == null)
        {
            instancia = new CartaMonstruoNula();
        }
        return instancia;
    }

    public CartaMonstruoNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
}
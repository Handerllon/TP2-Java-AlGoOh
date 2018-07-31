package Modelo.finDeJuego;

public final class CausaFinJuegoNula extends CausaFinJuego
{
    private static CausaFinJuegoNula instancia = null;

    private CausaFinJuegoNula()
    {
        super("", null);
    }

    public static CausaFinJuegoNula getInstancia()
    {
        if (instancia == null)
        {
            instancia = new CausaFinJuegoNula();
        }
        return instancia;
    }

    public CausaFinJuegoNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public String getNombreCausa()
    {
        return "";
    }

    public String getNombreJugador()
    {
        return "";
    }
}

package Vista.escena;

import Modelo.carta.monstruo.CartaMonstruo;

public final class EscenaNula implements Escena
{
    private static EscenaNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaNula()
    {

    }

    public static EscenaNula getInstancia()
    {
        if (instancia == null)
        {
            instancia = new EscenaNula();
        }
        return instancia;
    }

    public EscenaNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    @Override
    public Escena cambiarEscena()
    {
        return null;
    }

    @Override
    public void mostrar()
    {

    }

    @Override
    public void actualizarDibujo()
    {

    }

    @Override
    public void finDeJuego()
    {

    }

    @Override
    public void playMedia()
    {

    }

    @Override
    public void stopMedia()
    {

    }

    @Override
    public void cerrar()
    {

    }

    @Override
    public void solicitarCartaAAtacar(CartaMonstruo cartaAtacante)
    {

    }
}

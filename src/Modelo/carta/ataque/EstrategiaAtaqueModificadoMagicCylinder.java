package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public final class EstrategiaAtaqueModificadoMagicCylinder implements EstrategiaAtaque
{
    private static EstrategiaAtaqueModificadoMagicCylinder instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EstrategiaAtaqueModificadoMagicCylinder()
    {

    }

    public static EstrategiaAtaqueModificadoMagicCylinder getInstancia()
    {
        if (instancia == null)
        {
            instancia = new EstrategiaAtaqueModificadoMagicCylinder();
        }
        return instancia;
    }

    @Override
    public EstrategiaAtaqueModificadoMagicCylinder clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // ------------------------------------
    // Métodos de interfaz Ataque.
    // ------------------------------------
    @Override
    public void ejecutar(CartaMonstruo atacante, CartaMonstruo atacada)
    {
        this.ejecutar(atacante);
    }

    @Override
    public void ejecutar(CartaMonstruo atacante)
    {
        atacante.getPropietario().disminuirPuntosVida(atacante.getPuntosDeAtaque());
    }
}
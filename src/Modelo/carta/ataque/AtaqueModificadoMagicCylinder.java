package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public final class AtaqueModificadoMagicCylinder implements Ataque
{
    private static AtaqueModificadoMagicCylinder instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private AtaqueModificadoMagicCylinder()
    {

    }

    public static AtaqueModificadoMagicCylinder getInstancia()
    {
        if (instancia == null)
        {
            instancia = new AtaqueModificadoMagicCylinder();
        }
        return instancia;
    }

    @Override
    public AtaqueModificadoMagicCylinder clone() throws CloneNotSupportedException
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
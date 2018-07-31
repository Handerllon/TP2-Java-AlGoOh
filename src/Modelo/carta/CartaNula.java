package Modelo.carta;

import Modelo.Jugador;
import Modelo.observadores.ObservadorDeCarta;

public final class CartaNula extends Carta
{
    private static CartaNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private CartaNula()
    {

    }

    public static CartaNula getInstancia()
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

    public String getLocacionDeImagen()
    {

        return "resources/imagenes/cartaReverso.jpg";
    }

    public String getNombre()
    {
        return "";
    }

    public Jugador getPropietario()
    {
        return null;
    }

    public Jugador getOponente()
    {
        return null;
    }

    public void setOponente(Jugador oponente)
    {

    }

    // --------------------------------------------------------------------
    // Metodos por ser observable de Carta.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeCarta observador)
    {

    }

    @Override
    public void eliminarObservador(ObservadorDeCarta observador)
    {

    }

    @Override
    public void notificarCambioDeOrientacionDeCarta()
    {

    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void cambiarOrientacion()
    {

    }

    @Override
    public boolean estaBocaArriba()
    {
        return false;
    }

    @Override
    public boolean estaBocaAbajo()
    {
        return false;
    }

    // ------------------------------------
    // Métodos de tipo de carta.
    // ------------------------------------
    public boolean esCampo()
    {
        return false;
    }

    public boolean esMagica()
    {
        return false;
    }

    public boolean esMonstruo()
    {
        return false;
    }

    public boolean esTrampa()
    {
        return false;
    }

    public boolean esParteExodia()
    {
        return false;
    }
}

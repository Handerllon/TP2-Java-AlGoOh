package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.CartaObservable;
import Modelo.carta.ObservadorDeCarta;
import Modelo.carta.monstruo.CartaMonstruo;

import java.util.ArrayList;

public abstract class CartaTrampa extends Carta implements CartaObservable
{
    protected ArrayList<ObservadorDeCarta> observadoresDeCartas = new ArrayList<>();

    public CartaTrampa(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    // ------------------------------------
    // Metodos de observador de modelo.
    // ------------------------------------
    @Override
    public void agregarObsevador(ObservadorDeCarta observer)
    {
        this.observadoresDeCartas.add(observer);
    }

    @Override
    public void quitarObservador(ObservadorDeCarta observer)
    {
        this.observadoresDeCartas.remove(observer);
    }

    @Override
    public void notificarObservadores()
    {
        for (int i = 0; i < this.observadoresDeCartas.size(); i++)
        {
            this.observadoresDeCartas.get(i).notificarUsoDeCarta(this);
        }
    }

    // ------------------------------------
    // Metodos de efecto de carta trampa.
    // ------------------------------------
    public abstract void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada);

    public abstract void deshacerEfecto();

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
        return true;
    }
}

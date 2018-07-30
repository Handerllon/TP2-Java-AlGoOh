package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.observadores.ObservadorDeCartaTrampa;

import java.util.ArrayList;

public abstract class CartaTrampa extends Carta implements CartaTrampaObservable
{
    protected ArrayList<ObservadorDeCartaTrampa> observadoresDeCartasTrampa = new ArrayList<>();

    public CartaTrampa(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    // --------------------------------------
    // Metodos de observador de carta trampa.
    // --------------------------------------
    @Override
    public void registrarObsevadorCartaTrampa(ObservadorDeCartaTrampa observer)
    {
        this.observadoresDeCartasTrampa.add(observer);
    }

    @Override
    public void eliminarObservadorCartaTrampa(ObservadorDeCartaTrampa observer)
    {
        this.observadoresDeCartasTrampa.remove(observer);
    }

    @Override
    public void notificarEventoCartaTrampa()
    {
        observadoresDeCartasTrampa.forEach(observador -> observador.seUsoCartaTrampa(this));
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

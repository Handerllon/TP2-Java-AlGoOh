package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.CartaCampo;
import Modelo.carta.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.FinJuegoObservable;
import Modelo.finDeJuego.ObservadorFinJuego;

import java.util.ArrayList;

public class Modelo implements ModeloObservable, FinJuegoObservable, ObservadorFinJuego
{
    private Jugador jugador1;
    private Jugador jugador2;
    private ArrayList<ObservadorModelo> observadores = new ArrayList<>();
    private ArrayList<ObservadorFinJuego> observadoresFinJuegos = new ArrayList<>();

    public Modelo(String nombreJugador, String nombreOponente)
    {
        this.jugador1 = new Jugador(nombreJugador);
        this.jugador2 = new Jugador(nombreOponente);

        this.jugador1.establecerOponente(this.jugador2);
        this.jugador2.establecerOponente(this.jugador1);

        // Subscripciones a los eventos de fin de juego.
        this.jugador1.agregarObsevadorFinDeJuego(this);
        this.jugador2.agregarObsevadorFinDeJuego(this);

        this.jugador1.obtenerMazo().agregarObsevadorFinDeJuego(this);
        this.jugador2.obtenerMazo().agregarObsevadorFinDeJuego(this);

        this.jugador1.obtenerMano().agregarObsevadorFinDeJuego(this);
        this.jugador2.obtenerMano().agregarObsevadorFinDeJuego(this);
    }

    public Jugador obtenerJugador()
    {
        return this.jugador1;
    }

    public Jugador obtenerOponente()
    {
        return this.jugador2;
    }

    @Override
    public void agregarObsevador(ObservadorModelo observer)
    {
        this.observadores.add(observer);
    }

    @Override
    public void quitarObservador(ObservadorModelo observer)
    {
        this.observadores.remove(observer);
    }

    @Override
    public void notificarObservadores()
    {

        for (int i = 0; i < this.observadores.size(); i++)
        {

            this.observadores.get(i).actualizar();
        }
    }

    @Override
    public ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosJugador()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosOponente()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasJugador()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasOponente()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CartaCampo obtenerCartasEnRegionCampoJugador()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CartaCampo obtenerCartasEnRegionCampoOponente()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int obtenerNumeroDeCartasRestantesEnMazoJugador()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int obtenerNumeroDeCartasRestantesEnMazoOponente()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnLaManoDelJugador()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnLaManoDelOponente()
    {
        // TODO Auto-generated method stub
        return null;
    }

    // --------------------------------------------------------------------
    // Metodos de observadores de fin de juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorFinJuego observador)
    {
        if (this.observadoresFinJuegos.isEmpty() == false)
        {
            this.observadoresFinJuegos.remove(observador);
        }
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.finDeJuego(causaFinJuego));
    }

    @Override
    public void finDeJuego(CausaFinJuego causaFinJuego)
    {
        this.notificarFinDeJuego(causaFinJuego);
    }
}

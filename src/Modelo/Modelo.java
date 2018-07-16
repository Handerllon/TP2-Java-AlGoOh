package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.FinJuegoObservado;
import Modelo.finDeJuego.ObservadorDeFinJuego;

import java.util.ArrayList;

public class Modelo implements ModeloObservado, FinJuegoObservado, ObservadorDeFinJuego
{
    private Jugador jugador1;
    private Jugador jugador2;
    private ArrayList<ObservadorDeModelo> observadores = new ArrayList<>();
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuegos = new ArrayList<>();
    private CausaFinJuego causaFinJuego = new CausaFinJuegoNula();

    public Modelo(String nombreJugador, String nombreOponente)
    {
        this.jugador1 = new Jugador(nombreJugador);
        this.jugador2 = new Jugador(nombreOponente);

        this.jugador1.establecerOponente(this.jugador2);
        this.jugador2.establecerOponente(this.jugador1);

        // Subscripciones a los eventos de fin de juego.
        this.jugador1.agregarObsevador(this);
        this.jugador2.agregarObsevador(this);

        this.jugador1.obtenerMazo().agregarObsevador(this);
        this.jugador2.obtenerMazo().agregarObsevador(this);

        this.jugador1.obtenerMano().agregarObsevador(this);
        this.jugador2.obtenerMano().agregarObsevador(this);
    }

    public Jugador obtenerJugador()
    {
        return this.jugador1;
    }

    public Jugador obtenerOponente()
    {
        return this.jugador2;
    }

    public CausaFinJuego obtenerCausaFinJuego()
    {
        return this.causaFinJuego;
    }

    // --------------------------------------------------------------------
    // Metodos de observadores modelo.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevador(ObservadorDeModelo observer)
    {
        this.observadores.add(observer);
    }

    @Override
    public void quitarObservador(ObservadorDeModelo observer)
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
    public void agregarObsevador(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservador(ObservadorDeFinJuego observador)
    {
        if (this.observadoresFinJuegos.isEmpty() == false)
        {
            this.observadoresFinJuegos.remove(observador);
        }
    }

    @Override
    public void notificarObservadores(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.actualizar(causaFinJuego));
    }

    // El modelo es también un observador de fin de juego porque este le va a avisar al controlador cuando suceda
    // uno de esos eventos.
    @Override
    public void actualizar(CausaFinJuego causaFinJuego)
    {
        this.causaFinJuego = causaFinJuego;
        this.notificarObservadores(causaFinJuego);
    }
}

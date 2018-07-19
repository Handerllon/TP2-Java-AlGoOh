package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.finDeJuego.ObservadorDeFinJuego;

import java.util.ArrayList;

public final class Modelo implements ModeloObservable, FinDeJuegoObservable, ObservadorDeFinJuego
{
    private static Modelo instancia = null;
    private Jugador jugador1;
    private Jugador jugador2;
    private ArrayList<ObservadorDeModelo> observadoresDeModelo = new ArrayList<>();
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuego = new ArrayList<>();
    private CausaFinJuego causaFinJuego = CausaFinJuegoNula.obtenerInstancia();

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private Modelo()
    {
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();

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

    public static Modelo obtenerInstancia()
    {
        if (instancia == null)
        {
            instancia = new Modelo();
        }
        return instancia;
    }

    public Modelo clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void establecerNombreJugador(String nombreJugador)
    {
        this.jugador1.establecerNombre(nombreJugador);
    }

    public void establecerNombreOponente(String nombreJugador)
    {
        this.jugador2.establecerNombre(nombreJugador);
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
    // Metodos de observador de fin de juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuego.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        if (this.observadoresFinJuego.isEmpty() == false)
        {
            this.observadoresFinJuego.remove(observador);
        }
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuego.forEach(item -> item.seLlegoAFinDeJuego(causaFinJuego));
    }

    // El modelo es también un observador de fin de juego porque este le va a avisar al controlador cuando suceda
    // uno de esos eventos.
    @Override
    public void seLlegoAFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.causaFinJuego = causaFinJuego;
        this.notificarFinDeJuego(causaFinJuego);
    }

    // --------------------------------------------------------------------
    // Metodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevador(ObservadorDeModelo observer)
    {
        this.observadoresDeModelo.add(observer);
    }

    @Override
    public void quitarObservador(ObservadorDeModelo observer)
    {
        this.observadoresDeModelo.remove(observer);
    }

    @Override
    public void notificarObservadores()
    {

        for (int i = 0; i < this.observadoresDeModelo.size(); i++)
        {

            this.observadoresDeModelo.get(i).actualizarEstado();
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
        return this.jugador1.obtenerMazo().cantidadCartas();
    }

    @Override
    public int obtenerNumeroDeCartasRestantesEnMazoOponente()
    {
        return this.jugador2.obtenerMazo().cantidadCartas();
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

    public void terminar()
    {
        System.out.println("Terminando Modelo.");
    }
}

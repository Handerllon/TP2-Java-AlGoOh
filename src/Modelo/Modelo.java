package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
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

    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------
    @Override
    public ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosJugador()
    {
        return this.jugador1.obtenerRegionMonstruos().obtenerCartas();
    }

    @Override
    public ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosOponente()
    {
        return this.jugador2.obtenerRegionMonstruos().obtenerCartas();
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasJugador()
    {
        return this.jugador1.obtenerRegionMagicasYTrampas().obtenerCartas();
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasOponente()
    {
        return this.jugador2.obtenerRegionMagicasYTrampas().obtenerCartas();
    }

    @Override
    public ArrayList<CartaCampo> obtenerCartasEnRegionCampoJugador()
    {
        return this.jugador1.obtenerRegionCampo().obtenerCartas();
    }

    @Override
    public ArrayList<CartaCampo> obtenerCartasEnRegionCampoOponente()
    {
        return this.jugador2.obtenerRegionCampo().obtenerCartas();
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
        return this.jugador1.obtenerMano().obtenerCartas();
    }

    @Override
    public ArrayList<Carta> obtenerCartasEnLaManoDelOponente()
    {
        return this.jugador2.obtenerMano().obtenerCartas();
    }

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    @Override
    public void tomarCarta(Jugador jugador)
    {
        jugador.tomarCarta();
    }

    @Override
    public void jugarCartaMagicaBocaAbajo(Jugador jugador, Carta carta)
    {
        this.voltearBocaAbajo(carta);
        if (carta.esMagica() == true)
        {
            jugador.jugarCarta((CartaMagica) carta);
        }
    }

    @Override
    public void jugarCartaMagicaBocaArriba(Jugador jugador, Carta carta)
    {
        this.voltearBocaArriba(carta);
        if (carta.esMagica() == true)
        {
            jugador.jugarCarta((CartaMagica) carta);
        }
    }

    @Override
    public void jugarCartaTrampa(Jugador jugador, Carta carta)
    {
        if (carta.esTrampa() == true)
        {
            jugador.jugarCarta((CartaTrampa) carta);
        }
    }

    @Override
    public void activarCartaMagica(Jugador jugador, Carta carta)
    {
        if (carta.esMagica() == true)
        {
            this.voltearBocaArriba(carta);
            jugador.jugarCarta((CartaMagica) carta);
        }
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void voltearBocaAbajo(Carta carta)
    {
        if (carta.orientacionArriba() == true)
        {
            carta.cambiarOrientacion();
        }
    }

    @Override
    public void voltearBocaArriba(Carta carta)
    {
        if (carta.orientacionArriba() == false)
        {
            carta.cambiarOrientacion();
        }
    }

    @Override
    public void ponerEnModoAtaque(Carta carta)
    {
        if (carta.esMonstruo() == true)
        {
            if (((CartaMonstruo) carta).estaEnAtaque() == false)
            {
                ((CartaMonstruo) carta).cambiarModo();
            }
        }
    }

    @Override
    public void ponerEnModoDefensa(Carta carta)
    {
        if (carta.esMonstruo() == true)
        {
            if (((CartaMonstruo) carta).estaEnDefensa() == false)
            {
                ((CartaMonstruo) carta).cambiarModo();
            }
        }
    }

    @Override
    public void voltearCartaMonstruo(CartaMonstruo carta)
    {
        carta.cambiarOrientacion();
    }

    @Override
    public void cambiarModoCartaMonstruo(CartaMonstruo carta)
    {
        carta.cambiarModo();
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    @Override
    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        cartaAtacante.obtenerPropietario().atacarOponente(cartaAtacante, cartaAtacada);
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante)
    {
        cartaAtacante.obtenerPropietario().atacarOponente(cartaAtacante);
    }

    // ------------------------------------
    // Métodos de terminación.
    // ------------------------------------
    public void terminar()
    {
        System.out.println("Terminando Modelo.");
    }
}

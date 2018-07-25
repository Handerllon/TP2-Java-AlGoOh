package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.excepciones.NoEsUnaCartaMonstruo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.finDeJuego.ObservadorDeFinJuego;

import java.util.ArrayList;

public final class Modelo implements ModeloObservable, FinDeJuegoObservable, ObservadorDeFinJuego, IModelo
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

        this.jugador1.getMazo().agregarObsevadorFinDeJuego(this);
        this.jugador2.getMazo().agregarObsevadorFinDeJuego(this);

        this.jugador1.getMano().agregarObsevadorFinDeJuego(this);
        this.jugador2.getMano().agregarObsevadorFinDeJuego(this);
    }

    public static Modelo getInstancia()
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

    public void setNombreJugador(String nombreJugador)
    {
        this.jugador1.establecerNombre(nombreJugador);
    }

    public void setNombreOponente(String nombreJugador)
    {
        this.jugador2.establecerNombre(nombreJugador);
    }

    public CausaFinJuego getCausaFinJuego()
    {
        return this.causaFinJuego;
    }

    // ------------------------------------
    // Métodos de terminación.
    // ------------------------------------
    public void terminar()
    {
        System.out.println("Terminando Modelo.");
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

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
    @Override
    public ArrayList<CartaMonstruo> getCartasEnRegionMonstruosJugador()
    {
        return this.jugador1.obtenerRegionMonstruos().getCartas();
    }

    @Override
    public ArrayList<CartaMonstruo> getCartasEnRegionMonstruosOponente()
    {
        return this.jugador2.obtenerRegionMonstruos().getCartas();
    }

    @Override
    public ArrayList<Carta> getCartasEnRegionMagicasYTrampasJugador()
    {
        return this.jugador1.obtenerRegionMagicasYTrampas().getCartas();
    }

    @Override
    public ArrayList<Carta> getCartasEnRegionMagicasYTrampasOponente()
    {
        return this.jugador2.obtenerRegionMagicasYTrampas().getCartas();
    }

    @Override
    public ArrayList<CartaCampo> getCartasEnRegionCampoJugador()
    {
        return this.jugador1.obtenerRegionCampo().getCartas();
    }

    @Override
    public ArrayList<CartaCampo> getCartasEnRegionCampoOponente()
    {
        return this.jugador2.obtenerRegionCampo().getCartas();
    }

    @Override
    public int getCantidadCartasRestantesMazoJugador()
    {
        return this.jugador1.getMazo().cantidadCartas();
    }

    @Override
    public int getCantidadCartasRestantesMazoOponente()
    {
        return this.jugador2.getMazo().cantidadCartas();
    }

    @Override
    public ArrayList<Carta> getCartasManoJugador()
    {
        return this.jugador1.getMano().obtenerCartas();
    }

    @Override
    public ArrayList<Carta> getCartasManoOponente()
    {
        return this.jugador2.getMano().obtenerCartas();
    }

    public Jugador getJugador()
    {
        return this.jugador1;
    }

    public Jugador getOponente()
    {
        return this.jugador2;
    }

    // --------------------------------------------------------------------
    // Métodos de interfaz modelo.
    // --------------------------------------------------------------------
    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    @Override
    public void tomarCarta(Jugador jugador)
    {
        jugador.tomarCarta();
    }

    @Override
    public void setCartaMagica(Jugador jugador, Carta carta)
    {
        if (carta.esMagica() == true)
        {
            this.flipBocaAbajo(carta);
            jugador.enviarARegion((CartaMagica) carta);
        }
    }

    @Override
    public boolean requiereSacrificios(Carta carta)
    {
        if (carta.esMonstruo() == true)
        {
            return ((CartaMonstruo) carta).requiereSacrificio();
        } else
        {
            throw new NoEsUnaCartaMonstruo();
        }
    }

    @Override
    public void setCartaMonstruo(Carta carta)
    {
        if (carta.esMonstruo() == true)
        {
            carta.getPropietario().enviarARegion((CartaMonstruo) carta);
        } else
        {
            throw new NoEsUnaCartaMonstruo();
        }
    }

    @Override
    public boolean haySuficientesSacrificios(Carta carta)
    {
        if (carta.esMonstruo() == false)
        {
            throw new NoEsUnaCartaMonstruo();
        } else
        {
            if (((CartaMonstruo) carta).getEstrellas() >= 5)
            {
                return carta.getPropietario().obtenerRegionMonstruos().cantidadCartas() >= ((CartaMonstruo) carta)
                        .getEstrellas();
            }
            return true;
        }
    }

    @Override
    public void setCartaMonstruo(Carta carta, Sacrificio sacrificios)
    {
        if (carta.esMonstruo() == true)
        {
            carta.getPropietario().enviarARegion((CartaMonstruo) carta, sacrificios);
        } else
        {
            throw new NoEsUnaCartaMonstruo();
        }
    }

    @Override
    public void setCartaTrampa(Jugador jugador, Carta carta)
    {
        if (carta.esTrampa() == true)
        {
            this.flipBocaAbajo(carta);
            jugador.enviarARegion((CartaTrampa) carta);
        }
    }

    @Override
    public void activarCartaMagica(Jugador jugador, Carta carta)
    {
        if (carta.esMagica() == true)
        {
            this.flipBocaArriba(carta);
            jugador.enviarARegion((CartaMagica) carta);
        }
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void flipBocaAbajo(Carta carta)
    {
        if (carta.orientacionArriba() == true)
        {
            carta.cambiarOrientacion();
        }
    }

    @Override
    public void flipBocaArriba(Carta carta)
    {
        if (carta.orientacionArriba() == false)
        {
            carta.cambiarOrientacion();
        }
    }

    @Override
    public void setModoAtaque(Carta carta)
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
    public void setModoDefensa(Carta carta)
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
    public void flipCartaMonstruo(CartaMonstruo carta)
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
        cartaAtacante.getPropietario().atacarOponente(cartaAtacante, cartaAtacada);
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante)
    {
        cartaAtacante.getPropietario().atacarOponente(cartaAtacante);
    }
}

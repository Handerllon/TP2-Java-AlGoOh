package Modelo;

import Modelo.areaDeJuego.RegionMagicasYTrampas;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.excepciones.NoEsCartaMonstruo;
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
    private CausaFinJuego causaFinJuego = CausaFinJuegoNula.getInstancia();

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private Modelo()
    {
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();

        this.jugador1.setOponente(this.jugador2);
        this.jugador2.setOponente(this.jugador1);

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
        this.jugador1.setNombre(nombreJugador);
    }

    public void setNombreOponente(String nombreJugador)
    {
        this.jugador2.setNombre(nombreJugador);
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
        return this.jugador1.getRegionMonstruos().getCartas();
    }

    @Override
    public ArrayList<CartaMonstruo> getCartasEnRegionMonstruosOponente()
    {
        return this.jugador2.getRegionMonstruos().getCartas();
    }

    @Override
    public ArrayList<Carta> getCartasEnRegionMagicasYTrampasJugador()
    {
        return this.jugador1.getRegionMagicasYTrampas().getCartas();
    }

    @Override
    public ArrayList<Carta> getCartasEnRegionMagicasYTrampasOponente()
    {
        return this.jugador2.getRegionMagicasYTrampas().getCartas();
    }

    @Override
    public ArrayList<CartaCampo> getCartasEnRegionCampoJugador()
    {
        return this.jugador1.getRegionCampo().getCartas();
    }

    @Override
    public ArrayList<CartaCampo> getCartasEnRegionCampoOponente()
    {
        return this.jugador2.getRegionCampo().getCartas();
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
        return this.jugador1.getMano().getCartas();
    }

    @Override
    public ArrayList<Carta> getCartasManoOponente()
    {
        return this.jugador2.getMano().getCartas();
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
    public void setCartaMagica(Jugador jugador, CartaMagica carta)
    {
        this.flipBocaAbajo(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public void activarCartaMagica(CartaMagica carta)
    {
        this.flipBocaArriba(carta);
        carta.efecto();
    }

    @Override
    public void setCartaTrampa(Jugador jugador, CartaTrampa carta)
    {
        this.flipBocaAbajo(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public void setCartaMonstruo(CartaMonstruo carta) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        this.flipBocaAbajo(carta);
        this.setModoDefensa(carta);
        carta.getPropietario().enviarARegion(carta);
    }

    @Override
    public void setCartaMonstruo(CartaMonstruo carta, Sacrificio sacrificios) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        this.flipBocaAbajo(carta);
        this.setModoDefensa(carta);
        carta.getPropietario().enviarARegion(carta, sacrificios);
    }

    @Override
    public void summonCartaMonstruo(CartaMonstruo carta) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        this.flipBocaArriba(carta);
        this.setModoAtaque(carta);
        carta.getPropietario().enviarARegion(carta);
    }

    @Override
    public void summonCartaMonstruo(CartaMonstruo carta, Sacrificio sacrificios) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        this.flipBocaArriba(carta);
        this.setModoAtaque(carta);
        carta.getPropietario().enviarARegion(carta, sacrificios);
    }

    @Override
    public boolean requiereSacrificios(CartaMonstruo carta) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        return (carta).requiereSacrificio();
    }

    @Override
    public boolean haySuficientesSacrificios(CartaMonstruo carta) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }

        if (carta.getEstrellas() >= 5)
        {
            return carta.getPropietario().getRegionMonstruos().cantidadCartas() >= carta.getEstrellas();
        }
        return true;
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void flipBocaAbajo(Carta carta)
    {
        if (carta.estaBocaArriba())
        {
            carta.cambiarOrientacion();
        }
    }

    @Override
    public void flipBocaArriba(Carta carta)
    {
        if (!carta.estaBocaArriba())
        {
            carta.cambiarOrientacion();
        }
    }

    @Override
    public void setModoAtaque(CartaMonstruo carta) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        if (!carta.estaEnAtaque())
        {
            carta.cambiarModo();
        }
    }

    @Override
    public void setModoDefensa(CartaMonstruo carta) throws NoEsCartaMonstruo
    {
        if (!carta.esMonstruo())
        {
            throw new NoEsCartaMonstruo();
        }
        if (!carta.estaEnDefensa())
        {
            carta.cambiarModo();
        }
    }

    @Override
    public void flipCarta(Carta carta)
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
    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAAtacar)
    {
        cartaAtacante.getPropietario().atacar(cartaAtacante, cartaAAtacar);
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante)
    {
        cartaAtacante.getPropietario().atacar(cartaAtacante);
    }

    @Override
    public void activarFaseTrampa(Jugador solicitante){
        RegionMagicasYTrampas regionMyTOponente = solicitante.getOponente().getRegionMagicasYTrampas();
        regionMyTOponente.getCartaTrampaAUsar().efecto();
        // TODO: calcular el daño.
    }
}

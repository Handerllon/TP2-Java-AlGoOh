package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.CartaMonstruoNula;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.observadores.*;
import Modelo.region.Region;
import Modelo.region.RegionMagicasYTrampas;

import java.util.ArrayList;

public final class Modelo implements ModeloInterfaz, ModeloObservable, FinDeJuegoObservable, ObservadorDeFinJuego,
        ObservadorRegion, ObservadorDeMazo, ObservadorDeMano, ObservadorDeCarta, ObservadorDeJugador, ObservadorDeCartaMonstruo
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

        // Subscripciones a los eventos de Región, Mazo, Mano, Carta, y Jugador.
        this.jugador1.registrarObsevador(this);
        this.jugador2.registrarObsevador(this);

        this.jugador1.getRegiones().forEach(region -> region.registrarObsevador(this));
        this.jugador2.getRegiones().forEach(region -> region.registrarObsevador(this));

        this.jugador1.getMazo().registrarObsevador(this);
        this.jugador2.getMazo().registrarObsevador(this);

        this.jugador1.getMano().registrarObsevador(this);
        this.jugador2.getMano().registrarObsevador(this);

        this.jugador1.getMazo().getCartas().forEach(carta -> carta.registrarObsevador(this));
        this.jugador2.getMazo().getCartas().forEach(carta -> carta.registrarObsevador(this));

        this.jugador1.getMazo().getCartasMonstruo().forEach(cartaMonstruo -> cartaMonstruo.registrarObsevadorCartaMonstruo(this));
        this.jugador2.getMazo().getCartasMonstruo().forEach(cartaMonstruo -> cartaMonstruo.registrarObsevadorCartaMonstruo(this));
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
    // Metodos por ser un observable de Fin de Juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuego.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuego.remove(observador);
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuego.forEach(item -> item.seLlegoAFinDeJuego(causaFinJuego));
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Fin de Juego.
    // El modelo es también un observador de fin de juego porque este le
    // va a avisar al controlador cuando suceda uno de esos eventos.
    // --------------------------------------------------------------------
    @Override
    public void seLlegoAFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.causaFinJuego = causaFinJuego;
        this.notificarFinDeJuego(causaFinJuego);
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observable de Modelo.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeModelo observer)
    {
        this.observadoresDeModelo.add(observer);
    }

    @Override
    public void eliminarObservador(ObservadorDeModelo observer)
    {
        this.observadoresDeModelo.remove(observer);
    }

    @Override
    public void notificarTomaDeCartaDeMazo()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.seTomoCartaDeMazo());
    }

    @Override
    public void notificarIngresoCartaAMano()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.ingresoCartaAMano());
    }

    @Override
    public void notificarEgresoCartaAMano()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.egresoCartaAMano());
    }

    @Override
    public void notificarIngresoCartaARegion()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.ingresoCartaARegion());
    }

    @Override
    public void notificarEgresoCartaARegion()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.egresoCartaARegion());
    }

    @Override
    public void notificarCambioEnPuntosDeVida()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.cambiaronLosPuntosDeVida());
    }

    @Override
    public void notificarCambioDeOrientacionCarta()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.cartaCambioDeOrientacion());
    }

    @Override
    public void notificarCambioDeModoCarta()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.cartaCambioDeModo());
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Region, Mazo, Mano
    // --------------------------------------------------------------------
    @Override
    public void ingresoCarta(Region region)
    {
        notificarIngresoCartaARegion();
    }

    @Override
    public void egresoCarta(Region region)
    {
        notificarEgresoCartaARegion();
    }

    @Override
    public void cartaCambioDeOrientacion()
    {
        notificarCambioDeOrientacionCarta();
    }

    @Override
    public void cartaCambioDeModo()
    {
        notificarCambioDeModoCarta();
    }

    @Override
    public void seTomoCartaDeMazo()
    {
        notificarTomaDeCartaDeMazo();
    }

    @Override
    public void ingresoCartaAMano()
    {
        notificarIngresoCartaAMano();
    }

    @Override
    public void egresoCartaAMano()
    {
        notificarEgresoCartaAMano();
    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {
        notificarCambioEnPuntosDeVida();
    }

    public void agregarObservadorCartasTrampa(ObservadorDeCartaTrampa observador)
    {
        this.jugador1.getMazo().getCartasTrampa().forEach(cartaTrampa -> cartaTrampa.registrarObsevadorCartaTrampa(observador));
        this.jugador2.getMazo().getCartasTrampa().forEach(cartaTrampa -> cartaTrampa.registrarObsevadorCartaTrampa(observador));
    }

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
    @Override
    public ArrayList<CartaMonstruo> getCartasEnRegionMonstruosDe(Jugador jugador)
    {
        return jugador.getRegionMonstruos().getCartas();
    }

    @Override
    public ArrayList<Carta> getCartasEnRegionMagicasYTrampasDe(Jugador jugador)
    {
        return jugador.getRegionMagicasYTrampas().getCartas();
    }

    @Override
    public ArrayList<CartaCampo> getCartasEnRegionCampoDe(Jugador jugador)
    {
        return jugador.getRegionCampo().getCartas();
    }

    @Override
    public int getCantidadCartasRestantesMazoDe(Jugador jugador)
    {
        return jugador.getMazo().cantidadCartas();
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
    public void activarCartaMagicaDesdeRegionMyT(Jugador jugador, CartaMagica carta)
    {
        this.flipBocaArriba(carta);
        carta.efecto();
    }

    @Override
    public void activarCartaMagicaDesdeMano(Jugador jugador, CartaMagica carta)
    {
        this.flipBocaArriba(carta);
        carta.efecto();
        carta.getPropietario().getMano().quitarCarta(carta);
    }

    @Override
    public void setCartaTrampa(Jugador jugador, CartaTrampa carta)
    {
        this.flipBocaAbajo(carta);
        jugador.enviarARegion(carta);
    }

    // TODO: esto está mal. La carta  ya está en el campo.
    @Override
    public void activarCartaTrampa(Jugador jugador, CartaTrampa carta)
    {
        this.flipBocaArriba(carta);
        //jugador.enviarARegion(carta);
    }

    @Override
    public void activarCartaCampo(Jugador jugador, CartaCampo carta)
    {
        if (!jugador.getRegionCampo().estaVacia())
        {
            CartaCampo cartaCampo = jugador.getRegionCampo().getUltimaCartaEnEntrar();
            jugador.destruirCarta(cartaCampo);
        }
        this.flipBocaArriba(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public void setCartaMonstruo(Jugador jugador, CartaMonstruo carta)
    {
        this.flipBocaAbajo(carta);
        this.setModoDefensa(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public void summonCartaMonstruo(Jugador jugador, CartaMonstruo carta)
    {
        this.flipBocaArriba(carta);
        this.setModoAtaque(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public boolean seCumplenCondicionesDeSacrificiosRequeridos(CartaMonstruo carta)
    {
        return carta.seCumplenCondicionesDeSacrificiosRequeridos();
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
    public void setModoAtaque(CartaMonstruo carta)
    {
        if (!carta.estaEnAtaque())
        {
            carta.cambiarModo();
        }
    }

    @Override
    public void setModoDefensa(CartaMonstruo carta)
    {
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
        RegionMagicasYTrampas regionMyTOponente = cartaAtacante.getOponente().getRegionMagicasYTrampas();
        CartaTrampa cartaTrampaAUsar = regionMyTOponente.getCartaTrampaAUsar();
        this.flipBocaArriba(cartaTrampaAUsar);
        cartaTrampaAUsar.efecto(cartaAtacante, cartaAAtacar);

        cartaAtacante.atacar(cartaAAtacar);
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante)
    {
        RegionMagicasYTrampas regionMyTOponente = cartaAtacante.getOponente().getRegionMagicasYTrampas();
        // No la remueve, solamente tomo una referencia a la carta.
        CartaTrampa cartaTrampaAUsar = regionMyTOponente.getCartaTrampaAUsar();
        this.flipBocaArriba(cartaTrampaAUsar);
        cartaTrampaAUsar.efecto(cartaAtacante, CartaMonstruoNula.getInstancia());

        cartaAtacante.atacar();
    }
}

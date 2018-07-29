package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
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
        ObservadorRegion, ObservadorDeMazo, ObservadorDeMano, ObservadorDeCarta
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

        // Subscripciones a los eventos de Región, Mazo, Mano, y Carta.
        this.jugador1.getRegiones().forEach(region -> region.registrarObsevador(this));
        this.jugador2.getRegiones().forEach(region -> region.registrarObsevador(this));

        this.jugador1.getMazo().registrarObsevador(this);
        this.jugador2.getMazo().registrarObsevador(this);

        this.jugador1.getMano().registrarObsevador(this);
        this.jugador2.getMano().registrarObsevador(this);

        this.jugador1.getMazo().getCartas().forEach(carta -> carta.registrarObsevador(this));
        this.jugador2.getMazo().getCartas().forEach(carta -> carta.registrarObsevador(this));
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
    public void notificarEvento()
    {
        observadoresDeModelo.forEach(observadorDeModelo -> observadorDeModelo.huboCambios());
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Region, Mazo, Mano
    // --------------------------------------------------------------------
    @Override
    public void ingresoCarta(Region region)
    {

    }

    @Override
    public void egresoCarta(Region region)
    {

    }

    @Override
    public void huboCambios()
    {
        notificarEvento();
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

    // TODO: ver cómo hacer para que se active desde la mano.
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

    // TODO: esto está mal. La carta creo que ya está en el campo.
    @Override
    public void activarCartaTrampa(Jugador jugador, CartaTrampa carta)
    {
        this.flipBocaArriba(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public void activarCartaCampo(Jugador jugador, CartaCampo carta)
    {

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
    public void setCartaMonstruo(Jugador jugador, CartaMonstruo carta, Sacrificio sacrificios)
    {
        this.flipBocaAbajo(carta);
        this.setModoDefensa(carta);
        jugador.enviarARegion(carta, sacrificios);
    }

    @Override
    public void summonCartaMonstruo(Jugador jugador, CartaMonstruo carta)
    {
        this.flipBocaArriba(carta);
        this.setModoAtaque(carta);
        jugador.enviarARegion(carta);
    }

    @Override
    public void summonCartaMonstruo(Jugador jugador, CartaMonstruo carta, Sacrificio sacrificios)
    {
        this.flipBocaArriba(carta);
        this.setModoAtaque(carta);
        jugador.enviarARegion(carta, sacrificios);
    }

    @Override
    public boolean requiereSacrificios(CartaMonstruo carta)
    {
        return (carta).requiereSacrificio();
    }

    @Override
    public boolean haySuficientesCartasParaSacrificar(CartaMonstruo carta)
    {
        if (carta.getEstrellas() >= 5)
        {
            return carta.getPropietario().getRegionMonstruos().getCantidadCartas() >= carta.getEstrellas();
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
        cartaTrampaAUsar.efecto(cartaAtacante, cartaAAtacar);

        cartaAtacante.atacar(cartaAAtacar);
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante)
    {
        RegionMagicasYTrampas regionMyTOponente = cartaAtacante.getOponente().getRegionMagicasYTrampas();
        CartaTrampa cartaTrampaAUsar = regionMyTOponente.getCartaTrampaAUsar();
        cartaTrampaAUsar.efecto(cartaAtacante, CartaMonstruoNula.getInstancia());

        cartaAtacante.atacar();
    }
}

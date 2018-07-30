package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.mano.Mano;
import Modelo.carta.mazo.Mazo;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaPuntosDeVidaNulos;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.observadores.ObservadorDeFinJuego;
import Modelo.observadores.ObservadorDeJugador;
import Modelo.region.*;

import java.util.ArrayList;

public class Jugador implements FinDeJuegoObservable, JugadorObservable
{
    private static final int puntosDeVidaIniciales = 8000;
    // ----------------------------------------
    // Atributos varios.
    // ----------------------------------------
    private String nombre = "";
    private Jugador oponente;
    private int puntosDeVida = puntosDeVidaIniciales;
    private Mazo mazo;
    private Mano mano = new Mano(this);
    // ----------------------------------------
    // Regiones.
    // ----------------------------------------
    private RegionMonstruos regionMonstruos = new RegionMonstruos(this);
    private RegionMagicasYTrampas regionMagicasYTrampas = new RegionMagicasYTrampas(this);
    private RegionCampo regionCampo = new RegionCampo(this);
    private RegionCementerio regionCementerio = new RegionCementerio(this);
    // ----------------------------------------
    // Observadores.
    // ----------------------------------------
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuegos = new ArrayList<>();
    private ArrayList<ObservadorDeJugador> observadoresJugador = new ArrayList<>();

    public Jugador()
    {

    }

    public Jugador(String unNombre)
    {
        this.nombre = unNombre;
    }

    private void suscribirRegiones()
    {
        this.regionMonstruos.registrarObsevador(this.getRegionCampo());
        this.oponente.getRegionMonstruos().registrarObsevador(this.getRegionCampo());
    }

    private void crearMazo()
    {
        this.mazo = new Mazo(this, this.oponente);
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombreJugador)
    {
        this.nombre = nombreJugador;
    }

    public Jugador getOponente()
    {
        return this.oponente;
    }

    public void setOponente(Jugador oponente)
    {
        this.oponente = oponente;

        this.regionMonstruos.setOponente(oponente);
        this.regionMagicasYTrampas.setOponente(oponente);
        this.regionCampo.setOponente(oponente);
        this.regionCementerio.setOponente(oponente);

        this.suscribirRegiones();

        this.crearMazo();
    }

    public void disminuirPuntosVida(int puntosARestar)
    {
        this.puntosDeVida -= puntosARestar;
        this.notificarCambioEnPuntosDeVida();
        if (this.puntosDeVida <= 0)
        {
            this.notificarFinDeJuego(new CausaPuntosDeVidaNulos(this));
        }
    }

    // --------------------------------------------------------------------
    // Métodos de juego de cartas.
    // --------------------------------------------------------------------
    public void tomarCarta()
    {
        this.mano.agregarCarta(mazo.tomarCarta());
    }

    public void enviarARegion(CartaMonstruo cartaMonstruo, Sacrificio sacrificio)
    {
        cartaMonstruo.summon(sacrificio);
        this.mano.quitarCarta(cartaMonstruo);
    }

    public void enviarARegion(CartaMonstruo cartaMonstruo)
    {
        this.regionMonstruos.colocarCarta(cartaMonstruo);
        this.mano.quitarCarta(cartaMonstruo);
    }

    public void enviarARegion(CartaMagica cartaMagica)
    {
        this.regionMagicasYTrampas.colocarCarta(cartaMagica);
        this.mano.quitarCarta(cartaMagica);
    }

    public void enviarARegion(CartaCampo cartaCampo)
    {
        this.regionCampo.colocarCarta(cartaCampo);
        this.mano.quitarCarta(cartaCampo);
    }

    public void enviarARegion(CartaTrampa cartaTrampa)
    {
        this.regionMagicasYTrampas.colocarCarta(cartaTrampa);
        this.mano.quitarCarta(cartaTrampa);
    }

    public void destruirCarta(CartaMonstruo carta)
    {
        this.regionMonstruos.removerCarta(carta);
        this.regionCementerio.colocarCarta(carta);
    }

    public void destruirCarta(CartaTrampa carta)
    {
        this.regionMagicasYTrampas.removerCarta(carta);
        this.regionCementerio.colocarCarta(carta);
    }

    // --------------------------------------------------------------------
    // Métodos de juego de destrucción de cartas.
    // --------------------------------------------------------------------

    public void destruirCarta(CartaCampo carta)
    {
        this.regionCampo.removerCarta(carta);
        this.regionCementerio.colocarCarta(carta);
    }

    public void destruirMonstruos()
    {
        ArrayList<CartaMonstruo> cartas = this.regionMonstruos.getCartas();

        cartas.forEach(item -> this.regionCementerio.colocarCarta(item));

        this.regionMonstruos.removerTodasLasCartas();
    }

    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------
    public boolean cartaEstaEnCementerio(CartaMonstruo carta)
    {
        return this.regionCementerio.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return this.regionMonstruos.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMagicasYTrampa(Carta carta)
    {
        return this.regionMagicasYTrampas.contieneCarta(carta);
    }

    public int getPuntosDeVida()
    {
        return this.puntosDeVida;
    }

    public int getCantidadDeCartasEnMano()
    {

        return this.mano.cantidadDeCartas();
    }

    public CartaMonstruo getMonstruoConMenorAtaque()
    {

        return this.regionMonstruos.getMonstruoConMenorAtaque();
    }

    public ArrayList<CartaMonstruo> getCartasEnAreaMonstruo()
    {
        return this.regionMonstruos.getCartas();
    }

    public ArrayList<Region> getRegiones()
    {
        ArrayList<Region> regiones = new ArrayList<>();
        regiones.add(this.regionCampo);
        regiones.add(this.regionCementerio);
        regiones.add(this.regionMagicasYTrampas);
        regiones.add(this.regionMonstruos);

        return regiones;
    }

    public RegionMonstruos getRegionMonstruos()
    {
        return this.regionMonstruos;
    }

    public RegionCampo getRegionCampo()
    {
        return this.regionCampo;
    }

    public RegionMagicasYTrampas getRegionMagicasYTrampas()
    {

        return this.regionMagicasYTrampas;
    }

    public RegionCementerio getRegionCementerio()
    {

        return this.regionCementerio;
    }

    public Mazo getMazo()
    {

        return this.mazo;
    }

    public Mano getMano()
    {

        return this.mano;
    }

    // --------------------------------------------------------------------
    // Metodos de observadores de fin de juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.remove(observador);
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.seLlegoAFinDeJuego(causaFinJuego));
    }

    // --------------------------------------------------------------------
    // Metodos por ser observable de Jugador.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeJugador observador)
    {
        observadoresJugador.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorDeJugador observador)
    {
        observadoresJugador.remove(observador);
    }

    @Override
    public void notificarCambioEnPuntosDeVida()
    {
        observadoresJugador.forEach(observador -> observador.cambiaronLosPuntosDeVida());
    }
}


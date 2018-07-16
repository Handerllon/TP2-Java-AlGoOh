package Modelo;

import Modelo.areaDeJuego.RegionCampo;
import Modelo.areaDeJuego.RegionCementerio;
import Modelo.areaDeJuego.RegionMagicasYTrampas;
import Modelo.areaDeJuego.RegionMonstruos;
import Modelo.carta.Carta;
import Modelo.carta.Mano;
import Modelo.carta.Mazo;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaPuntosDeVidaNulos;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.finDeJuego.ObservadorDeFinJuego;

import java.util.ArrayList;

public class Jugador implements FinDeJuegoObservable
{
    // ----------------------------------------
    // Atributos varios.
    // ----------------------------------------
    private String nombre;
    private Jugador oponente;
    private int puntosDeVida;
    private Mazo mazo;
    private Mano mano;
    // ----------------------------------------
    // Regiones.
    // ----------------------------------------
    private RegionMonstruos regionMonstruos;
    private RegionMagicasYTrampas regionMagicasYTrampas;
    private RegionCampo regionCampo;
    private RegionCementerio cementerio;
    // ----------------------------------------
    // Observadores.
    // ----------------------------------------
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuegos = new ArrayList<>();

    public Jugador(String unNombre)
    {
        this.nombre = unNombre;
        this.puntosDeVida = 8000;

        this.mano = new Mano(this);

        this.regionMonstruos = new RegionMonstruos(this);
        this.regionMagicasYTrampas = new RegionMagicasYTrampas(this);
        this.regionCampo = new RegionCampo(this);
        this.cementerio = new RegionCementerio(this);
    }

    public void establecerOponente(Jugador oponente)
    {
        this.oponente = oponente;

        this.regionMonstruos.establecerOponente(oponente);
        this.regionMagicasYTrampas.establecerOponente(oponente);
        this.regionCampo.establecerOponente(oponente);
        this.cementerio.establecerOponente(oponente);

        this.suscribirRegiones();

        this.crearMazo();
    }

    private void suscribirRegiones()
    {
        this.regionMonstruos.agregarObsevador(this.obtenerRegionCampo());
        this.oponente.obtenerRegionMonstruos().agregarObsevador(this.obtenerRegionCampo());
    }

    private void crearMazo()
    {
        this.mazo = new Mazo(this, this.oponente);
    }

    public void tomarCarta()
    {

        this.mano.agregarCarta(mazo.tomarCarta());
    }

    public void disminuirPuntosVida(int puntosARestar)
    {
        this.puntosDeVida -= puntosARestar;
        if (this.puntosDeVida <= 0)
        {
            this.notificarObservadores(new CausaPuntosDeVidaNulos(this));
        }
    }

    // --------------------------------------------------------------------
    // Métodos de juego de cartas.
    // --------------------------------------------------------------------

    public void jugarCarta(CartaMonstruo cartaMonstruo, Sacrificio sacrificio)
    {
        cartaMonstruo.invocar(sacrificio);
    }

    public void jugarCarta(CartaMonstruo cartaMonstruo)
    {
        this.regionMonstruos.colocarCarta(cartaMonstruo);
        this.mano.quitarCarta(cartaMonstruo);
    }

    public void jugarCarta(CartaMagica cartaMagica)
    {
        this.regionMagicasYTrampas.colocarCarta(cartaMagica);
        this.mano.quitarCarta(cartaMagica);
    }

    public void jugarCarta(CartaCampo cartaCampo)
    {
        this.regionCampo.colocarCarta(cartaCampo);
        this.mano.quitarCarta(cartaCampo);
    }

    public void jugarCarta(CartaTrampa cartaTrampa)
    {
        this.regionMagicasYTrampas.colocarCarta(cartaTrampa);
        this.mano.quitarCarta(cartaTrampa);
    }

    // --------------------------------------------------------------------
    // Métodos de juego de destrucción de cartas.
    // --------------------------------------------------------------------

    public void destruirMonstruo(CartaMonstruo carta)
    {
        this.regionMonstruos.removerCarta(carta);
        this.cementerio.colocarCarta(carta);
    }

    public void destruirMonstruos()
    {
        ArrayList<CartaMonstruo> cartas = this.regionMonstruos.obtenerCartas();

        cartas.forEach(item -> this.cementerio.colocarCarta(item));

        this.regionMonstruos.removerTodasLasCartas();
    }

    public void removerCarta(CartaCampo carta)
    {
        this.regionCampo.removerCarta(carta);
        this.cementerio.colocarCarta(carta);
    }

    // --------------------------------------------------------------------
    // Métodos de ataque.
    // --------------------------------------------------------------------

    public void atacarOponente(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente)
    {
        cartaAtacante.atacarOponente(cartaOponente);
    }

    public void atacarOponente(CartaMonstruo cartaAtacante)
    {

        cartaAtacante.atacarOponente();
    }
    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------

    public int getPuntosDeVida()
    {
        return this.puntosDeVida;
    }

    public boolean cartaEstaEnCementerio(CartaMonstruo carta)
    {
        return this.cementerio.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return this.regionMonstruos.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMagicasYTrampa(Carta carta)
    {
        return this.regionMagicasYTrampas.contieneCarta(carta);
    }

    public int cantidadDeCartasEnMano()
    {

        return this.mano.cantidadDeCartas();
    }

    public CartaMonstruo obtenerMonstruoConMenorAtaque()
    {

        return this.regionMonstruos.obtenerMonstruoConMenorAtaque();
    }

    public ArrayList<CartaMonstruo> obtenerCartasEnAreaMonstruo()
    {
        return this.regionMonstruos.obtenerCartasEnRegion();
    }

    public CartaTrampa obtenerCartaTrampaAActivar()
    {

        return this.regionMagicasYTrampas.obtenerCartaTrampaAActivar();
    }

    public String obtenerNombre()
    {
        return this.nombre;
    }

    public RegionMonstruos obtenerRegionMonstruos()
    {
        return this.regionMonstruos;
    }

    public RegionCampo obtenerRegionCampo()
    {
        return this.regionCampo;
    }

    public RegionMagicasYTrampas obtenerRegionMagicasYTrampas()
    {

        return this.regionMagicasYTrampas;
    }

    public RegionCementerio obtenerCementerio()
    {

        return this.cementerio;
    }

    public Mazo obtenerMazo()
    {

        return this.mazo;
    }

    public Mano obtenerMano()
    {

        return this.mano;
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
}


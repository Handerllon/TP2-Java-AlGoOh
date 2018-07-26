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

    public Jugador()
    {
        this.nombre = "";
        this.puntosDeVida = 8000;

        this.mano = new Mano(this);

        this.regionMonstruos = new RegionMonstruos(this);
        this.regionMagicasYTrampas = new RegionMagicasYTrampas(this);
        this.regionCampo = new RegionCampo(this);
        this.cementerio = new RegionCementerio(this);
    }

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

    private void suscribirRegiones()
    {
        this.regionMonstruos.agregarObsevador(this.getRegionCampo());
        this.oponente.getRegionMonstruos().agregarObsevador(this.getRegionCampo());
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
            this.notificarFinDeJuego(new CausaPuntosDeVidaNulos(this));
        }
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
        this.cementerio.setOponente(oponente);

        this.suscribirRegiones();

        this.crearMazo();
    }

    public void enviarARegion(CartaMonstruo cartaMonstruo, Sacrificio sacrificio)
    {
        cartaMonstruo.summon(sacrificio);
        this.mano.quitarCarta(cartaMonstruo);
    }

    // --------------------------------------------------------------------
    // Métodos de juego de cartas.
    // --------------------------------------------------------------------

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
        ArrayList<CartaMonstruo> cartas = this.regionMonstruos.getCartas();

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

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAAtacar)
    {
        cartaAtacante.atacar(cartaAAtacar);
    }

    public void atacar(CartaMonstruo cartaAtacante)
    {
        cartaAtacante.atacar();
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

    public CartaMonstruo getMonstruoConMenorAtaque()
    {

        return this.regionMonstruos.getMonstruoConMenorAtaque();
    }

    public ArrayList<CartaMonstruo> getCartasEnAreaMonstruo()
    {
        return this.regionMonstruos.getCartas();
    }

    public CartaTrampa getCartaTrampaAActivar()
    {

        return this.regionMagicasYTrampas.getCartaTrampaAUsar();
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombreJugador)
    {
        this.nombre = nombreJugador;
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

    public RegionCementerio getCementerio()
    {

        return this.cementerio;
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
        if (this.observadoresFinJuegos.isEmpty() == false)
        {
            this.observadoresFinJuegos.remove(observador);
        }
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.seLlegoAFinDeJuego(causaFinJuego));
    }
}


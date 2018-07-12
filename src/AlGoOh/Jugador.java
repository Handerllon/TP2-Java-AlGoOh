package AlGoOh;

import AlGoOh.excepciones.JugadorSinVida;
import areaDeJuego.RegionCampo;
import areaDeJuego.RegionCementerio;
import areaDeJuego.RegionMagicasYTrampas;
import areaDeJuego.RegionMonstruos;
import carta.*;

import java.util.ArrayList;

public class Jugador
{
    private String nombre;
    private Mazo mazo;
    private Mano cartasEnMano;
    private int puntosDeVida;
    private RegionMonstruos regionMonstruos;
    private RegionMagicasYTrampas regionMagicasYTrampas;
    private RegionCampo regionCampo;
    private RegionCementerio cementerio;
    private Jugador oponente;

    public Jugador(String unNombre)
    {
        this.nombre = unNombre;
        this.puntosDeVida = 8000;

        this.cartasEnMano = new Mano();

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
    }

    public void suscribirRegiones()
    {
        this.regionMonstruos.suscribirRegionANotificar(this.obtenerRegionCampo());
        this.oponente.obtenerRegionMonstruos().suscribirRegionANotificar(this.obtenerRegionCampo());
    }

    public void crearMazo()
    {
        this.mazo = new Mazo(this, this.oponente);
        int cartasATomarInicialmente = 5;
        this.popularMano(cartasATomarInicialmente);
    }

    private void popularMano(int cartasATomarInicialmente)
    {

        for (int i = 0; i < cartasATomarInicialmente; i++)
        {
            this.cartasEnMano.agregarCarta(mazo.tomarCarta());
        }
    }

    public void tomarCarta()
    {
        Carta cartaAQuitar;
        while (this.cartasEnMano.manoLlena())
        {
            cartaAQuitar = this.cartasEnMano.quitarUltimaCarta();
            this.cementerio.colocarCarta(cartaAQuitar);
        }

        this.cartasEnMano.agregarCarta(mazo.tomarCarta());
    }

    public void disminuirPuntosVida(int puntosARestar)
    {
        this.puntosDeVida -= puntosARestar;
        if (this.puntosDeVida <= 0)
            // TODO: Esto puede ser un trigger para terminar el juego.
            throw new JugadorSinVida();
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
        this.cartasEnMano.quitarCarta(cartaMonstruo);
    }

    public void jugarCarta(CartaMagica cartaMagica)
    {
        this.regionMagicasYTrampas.colocarCarta(cartaMagica);
        this.cartasEnMano.quitarCarta(cartaMagica);
    }

    public void jugarCarta(CartaCampo cartaCampo)
    {
        this.regionCampo.colocarCarta(cartaCampo);
        this.cartasEnMano.quitarCarta(cartaCampo);
    }

    public void jugarCarta(CartaTrampa cartaTrampa)
    {
        this.regionMagicasYTrampas.colocarCarta(cartaTrampa);
        this.cartasEnMano.quitarCarta(cartaTrampa);
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

        return this.cartasEnMano.cantidadDeCartas();
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

    //Obtener de regiones
    public RegionMonstruos obtenerRegionMonstruos()
    {
    	return this.regionMonstruos;
    }
    
    public RegionCampo obtenerRegionCampo()
    {
    	return this.regionCampo;
    }
    
    public RegionMagicasYTrampas obtenerRegionMagicasYTrampas(){
    	
    	return this.regionMagicasYTrampas;
    }
    
    public RegionCementerio obtenerCementerio(){
    	
    	return this.cementerio;
    }
    
    public Mazo obtenerMazo(){
    	
    	return this.mazo;
    }
    
    public Mano obtenerMano(){
    	
    	return this.cartasEnMano;
    }
}


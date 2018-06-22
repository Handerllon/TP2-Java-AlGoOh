package areaDeJuego;

import AlGoOh.Jugador;
import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.Carta;
import carta.CartaCampo;
import carta.CartaMagica;
import carta.CartaMonstruo;
import carta.Sacrificio;

import java.util.ArrayList;

public class AreaDeCartas
{

    private RegionMonstruos regionMonstruos;
    private RegionMagicasYTrampas regionMagicasYTrampas;
    private RegionCampo regionCampo;
    private RegionCementerio cementerio;

    private Jugador jugadorAsociado;


    public AreaDeCartas(Jugador jugador)
    {

        this.regionMonstruos = new RegionMonstruos();
        this.regionMagicasYTrampas = new RegionMagicasYTrampas();
        this.regionCampo = new RegionCampo();
        this.cementerio = new RegionCementerio();

        this.jugadorAsociado = jugador;
    }

    // --------------------------------------------------------------------
    // Métodos de agregación de cartas.
    // --------------------------------------------------------------------

    public void agregarCarta(CartaMonstruo carta)
    {
        this.regionMonstruos.agregarCarta(carta, this.cementerio);
    }

    public void agregarCarta(CartaMonstruo carta, Sacrificio sacrificio)
    {
        this.regionMonstruos.agregarCarta(carta, sacrificio, this.cementerio);
    }

    // TODO: Cambiar implementacion para que coincida con agregar carta de monstruos, se sacaria la verificacion de aca
    public void agregarCarta(CartaMagica carta)
    {
        if (this.regionMagicasYTrampas.hayEspacioLibre())
            this.regionMagicasYTrampas.colocarCarta(carta);
        else
            throw new RegionSinEspacioLibre(this.regionMagicasYTrampas);
    }
    
    public void agregarCarta(CartaCampo cartaDeCampo) {
    	
    	this.regionCampo.agregarCarta(cartaDeCampo);
    }

    public void removerCarta(CartaMonstruo carta)
    {
        this.regionMonstruos.removerCarta(carta);
    }

    // --------------------------------------------------------------------
    // Métodos de movimiento de cartas.
    // --------------------------------------------------------------------

    public void enviarAlCementerio(Carta carta)
    {
        this.cementerio.colocarCarta(carta);
    }

    public void enviarMonstruosAlCementerio()
    {
        ArrayList<Carta> cartas = this.regionMonstruos.obtenerCartas();

        cartas.forEach(item -> this.enviarAlCementerio(item));

        this.regionMonstruos.removerTodasLasCartas();
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------

    public boolean cartaEstaEnCementerio(Carta carta)
    {
        return this.cementerio.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return this.regionMonstruos.contieneCarta(carta);
    }
}

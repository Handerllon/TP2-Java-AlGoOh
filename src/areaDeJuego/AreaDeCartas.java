package areaDeJuego;

import AlGoOh.Jugador;
import areaDeJuego.excepciones.RegionMagicasYTrampasSinEspacioLibre;
import areaDeJuego.excepciones.RegionMonstruoSinEspacioLibre;
import carta.Carta;
import carta.magica.CartaMagica;
import carta.monstruo.CartaMonstruo;

import java.util.ArrayList;

public class AreaDeCartas
{

    private RegionMonstruos regionMonstruos;
    private RegionMagicasYTrampas regionMagicasYTrampas;
    private RegionCampo regionCampo;
    private Cementerio cementerio;
    private Jugador jugadorAsociado;


    public AreaDeCartas(Jugador jugador)
    {

        this.regionMonstruos = new RegionMonstruos();

        this.regionMagicasYTrampas = new RegionMagicasYTrampas();

        this.regionCampo = new RegionCampo();

        this.cementerio = new Cementerio();

        this.jugadorAsociado = jugador;
    }

    public void enviarAlCementerio(Carta carta)
    {
        this.cementerio.colocarCarta(carta);
    }

    public void enviarTodosMonstruosAlCementerio()
    {
        ArrayList<Carta> cartas = this.regionMonstruos.obtenerCartas();

        cartas.forEach(item -> this.enviarAlCementerio(item));

        this.regionMonstruos.removerTodasLasCartas();
    }

    public boolean cartaEstaEnCementerio(Carta carta)
    {
        return this.cementerio.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return this.regionMonstruos.contieneCarta(carta);
    }

    public void agregarCarta(CartaMonstruo carta)
    {
        if(regionMonstruos.hayEspacioLibre())
            regionMonstruos.colocarCarta(carta);
        else
            throw new RegionMonstruoSinEspacioLibre();
    }

    public void agregarCarta(CartaMagica cartaJugador)
    {
        if(regionMagicasYTrampas.hayEspacioLibre())
            regionMagicasYTrampas.colocarCarta(cartaJugador);
        else
            throw new RegionMagicasYTrampasSinEspacioLibre();
    }


    public boolean noTieneMonstruos()
    {
        return regionMonstruos.estaVacia();
    }

    public boolean tieneMonstruos()
    {
        return !this.noTieneMonstruos();
    }

    public void removerCarta(CartaMonstruo carta)
    {
        regionMonstruos.removerCarta(carta);
    }
}

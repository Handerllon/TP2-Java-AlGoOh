package areaDeJuego;

import AlGoOh.Jugador;
import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.Carta;
import carta.CartaMonstruo;
import carta.excepciones.CartaNoExisteEnRegion;
import vistaJuego.Observador;

import java.util.ArrayList;

public abstract class Region<T extends Carta> implements Notificable, Observable
{
    protected ArrayList<T> cartas = new ArrayList<>();
    protected ArrayList<Observador> observadores = new ArrayList<>();
    protected int capacidadMaxima;
    protected Jugador jugador, oponente;
    protected ArrayList<Region> regionesANotificar = new ArrayList<>();

    public Region(int capacidadMaxima, Jugador jugador)
    {
        this.capacidadMaxima = capacidadMaxima;
        this.jugador = jugador;
    }

    public void establecerOponente(Jugador oponente)
    {
        this.oponente = oponente;
    }

    public void suscribirRegionANotificar(Region region)
    {
        this.regionesANotificar.add(region);
    }

    public void colocarCarta(T carta)
    {
        if (this.hayEspacioLibre())
        {
            cartas.add(carta);
        } else
            throw new RegionSinEspacioLibre(this);
    }

    public void removerCarta(T carta)
    {
        if (this.contieneCarta(carta))
        {
            this.cartas.remove(carta);
        } else
            throw new CartaNoExisteEnRegion(carta);
    }

    public boolean contieneCarta(T carta)
    {
        return cartas.contains(carta);
    }

    public ArrayList<T> obtenerCartas()
    {
        return new ArrayList<>(this.cartas);
    }

    public void removerTodasLasCartas()
    {
        this.cartas.clear();
    }

    public boolean hayEspacioLibre()
    {
        return this.cartas.size() < this.capacidadMaxima;
    }

    public ArrayList<T> obtenerCartasEnRegion()
    {
        return this.cartas;
    }

    public void notificarColocacionDeCarta(CartaMonstruo carta)
    {

    }

    public void notificarRemocionDeCarta(CartaMonstruo carta)
    {

    }
    

	@Override
	public void subscribir(Observador observer) {
		this.observadores.add(observer);
		
	}

	@Override
	public void desubscribir(Observador observer) {
		this.observadores.remove(observer);
		
	}

	@Override
	public void notificar() {
		
		for (int i = 0 ; i<this.observadores.size(); i++){
			
			this.observadores.get(i).update(this.obtenerCartasEnRegion());
		}
		
	}
}
package Modelo;

import Observador.ModeloObservable;
import Observador.ObjetoObservador;

import java.util.ArrayList;

import Modelo.carta.Carta;
import Modelo.carta.CartaCampo;
import Modelo.carta.CartaMonstruo;

public class Modelo implements ModeloObservable
{
    private Jugador jugador1;
    private Jugador jugador2;

    private ArrayList<ObjetoObservador> observadores = new ArrayList<>();

    public Modelo(String nombreJugador, String nombreOponente)
    {
        this.jugador1 = new Jugador(nombreJugador);
        this.jugador2 = new Jugador(nombreOponente);

        this.jugador1.establecerOponente(this.jugador2);
        this.jugador2.establecerOponente(this.jugador1);

        this.jugador1.crearMazo();
        this.jugador2.crearMazo();
    }

    
    public Jugador obtenerJugador()
    {
        return this.jugador1;
    }

    
    public Jugador obtenerOponente()
    {
        return this.jugador2;
    }

    @Override
    public void agregarObsevador(ObjetoObservador observer)
    {
        this.observadores.add(observer);
    }

    @Override
    public void quitarObservador(ObjetoObservador observer)
    {
        this.observadores.remove(observer);
    }

    @Override
    public void notificarObservadores()
    {

        for (int i = 0; i < this.observadores.size(); i++)
        {

            this.observadores.get(i).actualizar();
        }
    }

	@Override
	public ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosOponente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasOponente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartaCampo obtenerCartasEnRegionCampoJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartaCampo obtenerCartasEnRegionCampoOponente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int obtenerNumeroDeCartasRestantesEnMazoJugador() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int obtenerNumeroDeCartasRestantesEnMazoOponente() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Carta> obtenerCartasEnLaManoDelJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carta> obtenerCartasEnLaManoDelOponente() {
		// TODO Auto-generated method stub
		return null;
	}
}

package carta;

import carta.excepciones.NoSePuedenAgregarMasCartasALaMano;
import vistaJuego.Observador;

import java.util.ArrayList;
import java.util.LinkedList;

import areaDeJuego.Observable;

public class Mano implements Observable
{
    private static int CANTIDAD_MAXIMA = 6;
    private LinkedList<Carta> cartas;
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Mano()
    {
        this.cartas = new LinkedList<>();
    }

    public void agregarCarta(Carta carta)
    {
        if (this.cantidadDeCartas() <= CANTIDAD_MAXIMA)
        {
            cartas.add(carta);
        } else throw new NoSePuedenAgregarMasCartasALaMano();
    }

    public void quitarCarta(Carta carta)
    {
        this.cartas.remove(carta);
    }

    public Carta quitarUltimaCarta()
    {
        return this.cartas.removeLast();
    }

    public int cantidadDeCartas()
    {
        return cartas.size();
    }

    public boolean manoLlena()
    {
        return this.cantidadDeCartas() == this.CANTIDAD_MAXIMA;
    }
    /*
    private ArrayList<Carta> obtenerCartasEnMano(){
    	
    	return this.cartas;
    }
*/
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
			
			//this.observadores.get(i).update(this.obtenerCartasEnMano);
		}
		
	}
}

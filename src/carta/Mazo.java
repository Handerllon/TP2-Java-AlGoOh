package carta;

import AlGoOh.EstadosJuego.ObservadorFinJuego;
import areaDeJuego.Observable;
import AlGoOh.Jugador;
import carta.excepciones.MazoVacio;
import vistaJuego.Observador;

import java.util.ArrayList;
import java.util.Stack;

public class Mazo implements Observable
{
    private Stack<Carta> cartas = new Stack<>();
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Mazo(Jugador jugador, Jugador oponente)
    {
        //En el mazo hay un maximo de 40 cartas, con las cuales se comienza el juego
        int cantidadCartas = 40;

        FabricaCartas fabricaCartas = new FabricaCartas(jugador, oponente);

        ArrayList<String> nombresMonstruosNoNormales = fabricaCartas.obtenerNombresCartasMonstruosNoNormales();
        for (int i = 0; i < nombresMonstruosNoNormales.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMonstruo(nombresMonstruosNoNormales.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresMagicas = fabricaCartas.obtenerNombresCartasMagicas();
        for (int i = 0; i < nombresMagicas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMagica(nombresMagicas.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresCampo = fabricaCartas.obtenerNombresCartasCampo();
        for (int i = 0; i < nombresCampo.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaCampo(nombresCampo.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresTrampas = fabricaCartas.obtenerNombresCartasTrampa();
        for (int i = 0; i < nombresTrampas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaTrampa(nombresTrampas.get(i)));
            cantidadCartas--;
        }

        // Agrego todas las que se pueden repetir.
        ArrayList<String> nombresMonstruosNormales = fabricaCartas.obtenerNombresCartasMonstruosNormales();
        for (int i = 0; cantidadCartas > 0; i++)
        {
            if (i == nombresMonstruosNormales.size())
                i = 0;
            cartas.push(fabricaCartas.crearCartaMonstruo(nombresMonstruosNormales.get(i)));
            cantidadCartas--;
        }

        this.mezclar();
    }

    private void mezclar()
    {
        Stack<Carta> mazoTemporal = new Stack<Carta>();
        while (!this.cartas.isEmpty())
        {
            int loc = (int) (Math.random() * cartas.size());
            mazoTemporal.push(this.cartas.get(loc));
            this.cartas.remove(loc);
        }
        this.cartas = mazoTemporal;
    }

    public Carta tomarCarta()
    {
        if (cartas.size() > 0)
        {
            Carta carta = cartas.pop();
            return carta;
        } else
        {
            throw new MazoVacio();
        }
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
			
			//this.observadores.get(i).update(this.obtenerCartasEnMano);
		}
		
	}
	
	
}

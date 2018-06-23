package areaDeJuego;

import java.util.HashMap;
import java.util.Iterator;

import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.Carta;
import carta.CartaMonstruo;
import carta.Sacrificio;
import carta.excepciones.NoHayCartasParaSacrificarError;

public class RegionMonstruos extends Region
{
    private static int CAPACIDAD_REGION_MONSTRUOS = 5;
	private int modificadorAtaque;
	private int modificadorDefensa;
	private HashMap<String,CartaMonstruo> cartas;

    public RegionMonstruos()
    {
        super(CAPACIDAD_REGION_MONSTRUOS);
        
        this.modificadorAtaque = 0;
        this.modificadorDefensa = 0;
        this.cartas = new HashMap<String,CartaMonstruo>();
    }

    public void agregarCarta(CartaMonstruo carta, RegionCementerio cementerio)
    {

        if (hayEspacioLibre())
        {

            this.colocarCarta(carta, cementerio);
        } else
            throw new RegionSinEspacioLibre(this);

    }

    public void agregarCarta(CartaMonstruo carta, Sacrificio sacrificio, RegionCementerio cementerio)
    {

        if (hayEspacioLibre())
        {

            this.colocarCarta(carta, sacrificio, cementerio);
        } else
            throw new RegionSinEspacioLibre(this);

    }

    private void colocarCarta(CartaMonstruo carta, RegionCementerio cementerio)
    {

        if (carta.getEstrellas() >= 5)
        {
            throw new NoHayCartasParaSacrificarError();
        }

        insertarCarta(carta);
    }

    private void colocarCarta(CartaMonstruo carta, Sacrificio sacrificios, RegionCementerio cementerio)
    {

        if (carta.getEstrellas() >= 5 && carta.getEstrellas() <= 6)
        {
            this.destruirMonstruo(sacrificios.getMonstruo(), cementerio);
        } else if (carta.getEstrellas() >= 7)
        {

            this.destruirMonstruo(sacrificios.getMonstruo(), cementerio);
            this.destruirMonstruo(sacrificios.getMonstruo(), cementerio);
        }

        insertarCarta(carta);
    }

    private void destruirMonstruo(CartaMonstruo carta, RegionCementerio cementerio)
    {

        cementerio.colocarCarta(carta);
        this.removerCarta(carta);
    }

	public void modificarAtaqueMonstruosCon(int modificadorAtaque) {
		
		this.modificadorAtaque = this.modificadorAtaque + modificadorAtaque;
		
	}
	
	public void modificarDefensaMonstruosCon(int modificadorDefensa) {
		
		this.modificadorDefensa = this.modificadorDefensa + modificadorDefensa;
		
	}

	public int obtenerModificadorDePuntosDeAtaque() {
		
		return this.modificadorAtaque;
	}
	
	public int obtenerModificadorDePuntosDeDefensa() {
		
		return this.modificadorDefensa;
	}

	public CartaMonstruo obtenerMonstruoConMenorAtaque() {
		
		CartaMonstruo cartaConAtaqueMinimo = null;
		int ataqueTope = 100000;
		Iterator<HashMap.Entry<String, CartaMonstruo>> iterator = cartas.entrySet().iterator();
	    while (iterator.hasNext()) {
	        HashMap.Entry<String, CartaMonstruo> entry = iterator.next();
	        if (entry.getValue().obtenerPuntosDeAtaque() < ataqueTope){
	        	ataqueTope = entry.getValue().obtenerPuntosDeAtaque();
	        	cartaConAtaqueMinimo = entry.getValue();
	        }
	    }
	    
	    return cartaConAtaqueMinimo;
	}


}

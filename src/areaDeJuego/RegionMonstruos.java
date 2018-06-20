package areaDeJuego;

import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.CartaMonstruo;
import carta.Sacrificio;

public class RegionMonstruos extends Region
{
    private static int CAPACIDAD_REGION_MONSTRUOS = 5;

    public RegionMonstruos()
    {
        super(CAPACIDAD_REGION_MONSTRUOS);
    }

	public void agregarCarta(CartaMonstruo carta, Sacrificio sacrificio, RegionCementerio cementerio) {

		if(hayEspacioLibre()){
			
			this.colocarCarta(carta, sacrificio, cementerio);
		}
		else 
			throw new RegionSinEspacioLibre(this);

	}

	private void colocarCarta(CartaMonstruo carta, Sacrificio sacrificios, RegionCementerio cementerio) {
		
		if (carta.getEstrellas() >= 5 && carta.getEstrellas() <= 6){
			this.destruirMonstruo(sacrificios.getMonstruo() , cementerio);
	    } 
		else if (carta.getEstrellas() >= 7){
	                
			this.destruirMonstruo(sacrificios.getMonstruo() , cementerio);
			this.destruirMonstruo(sacrificios.getMonstruo() , cementerio);
	    }	
	    
		insertarCarta(carta);
	}

	private void destruirMonstruo(CartaMonstruo carta, RegionCementerio cementerio) {
		
		cementerio.colocarCarta(carta);
		this.removerCarta(carta);
	}
    
}

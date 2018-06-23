package areaDeJuego;

import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.CartaCampo;

public class RegionCampo extends Region
{
    private static int CAPACIDAD_REGION_CAMPO = 1;

    public RegionCampo()
    {
        super(CAPACIDAD_REGION_CAMPO);
    }

	public void agregarCarta(CartaCampo cartaDeCampo) {
		
		if(hayEspacioLibre()){
			
			insertarCarta(cartaDeCampo);
		}
		
		else 
			throw new RegionSinEspacioLibre(this);
	}
	
}

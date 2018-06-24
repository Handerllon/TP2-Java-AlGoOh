package carta.monstruo;

import javax.swing.text.html.HTMLDocument.Iterator;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;
import carta.excepciones.NoHayCartasParaSacrificarError;

public class BlueEyesUltimateDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 3800;
    private static int PUNTOS_ATAQUE = 4500;

    public BlueEyesUltimateDragon(Jugador jugador, Jugador oponente)
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente);
        this.estrellas = 12;
        this.nombre = "Blue-Eyes Ultimate Dragon";
    }
    
    
    public void invocar(Sacrificio sacrificio){
    	
    	int cantidadDragonesBlancosDeOjosAzules = 0;
    	int cantidadDeSacrificiosIniciales = sacrificio.cantidadDeSacrificios();
    	Iterator<CartaMonstruo> iteradorSacrificios = sacrificio.getIteratorCartasASacrificar();
    	
    	for (int i=0 ; i<cantidadDeSacrificiosIniciales; i++){
    		
    		if (sacrificio.getIterator() == "Blue-Eyes White Dragon"){
    			
    			cantidadDragonesBlancosDeOjosAzules++;
    		}
    	}
    	
    	if (cantidadDragonesBlancosDeOjosAzules == 3){
    		
    		for (int i=0 ; i<cantidadDeSacrificiosIniciales; i++){
        		
        		if (sacrificio.getMonstruo().obtenerNombre() == "Blue-Eyes White Dragon"){
        			
        			this.jugador.destruirMonstruo(sacrificio.getMonstruo());;
        		}
        	}
    		
    	}
    	
    }

}

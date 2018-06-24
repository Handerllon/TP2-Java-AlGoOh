package carta;

import carta.excepciones.NoHayCartasParaSacrificarError;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;


// TODO: validar la cantidad de cartas que voy poniendo. Puede lanzar excepciones si pido de más.
public class Sacrificio
{
    private ArrayList<CartaMonstruo> cartasASacrificar;

    public Sacrificio()
    {
        this.cartasASacrificar = new ArrayList<CartaMonstruo>();
    }

    public void agregarCarta(CartaMonstruo carta)
    {
        this.cartasASacrificar.add(carta);
    }

    public CartaMonstruo getMonstruo()
    {
    	
    	if (cartasASacrificar.isEmpty()){
    		
    		throw new NoHayCartasParaSacrificarError();
    	}
    	
    	CartaMonstruo cartaMonstruo;
    	cartaMonstruo = cartasASacrificar.get(cartasASacrificar.size()-1);
    	cartasASacrificar.remove(cartasASacrificar.size()-1);	
    	
    	
        return cartaMonstruo;
    }

	public int cantidadDeSacrificios() {
		
		return this.cartasASacrificar.size();
	}
	
	public Iterator<CartaMonstruo> getIteratorCartasASacrificar(){
		
		return cartasASacrificar.iterator();
	}
}

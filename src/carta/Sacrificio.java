package carta;

import java.util.EmptyStackException;
import java.util.Stack;

import carta.excepciones.NoHayCartasParaSacrificarError;


// TODO: validar la cantidad de cartas que voy poniendo. Puede lanzar excepciones si pido de más.
public class Sacrificio
{
    private Stack<CartaMonstruo> cartasASacrificar;

    public Sacrificio()
    {
        this.cartasASacrificar = new Stack<CartaMonstruo>();
    }

    public void agregarCarta(CartaMonstruo carta)
    {
        this.cartasASacrificar.push(carta);
    }

    public CartaMonstruo getMonstruo()
    {
    	CartaMonstruo cartaMonstruo;
    	
    	try{
    		cartaMonstruo = cartasASacrificar.pop();		
    	}
    	catch(EmptyStackException e){
    		
    		throw new NoHayCartasParaSacrificarError();
    	}
    	
        return cartaMonstruo;
    }
}

package carta;

import carta.excepciones.NoHayCartasParaSacrificarError;

import java.util.Iterator;
import java.util.LinkedList;


// TODO: validar la cantidad de cartas que voy poniendo. Puede lanzar excepciones si pido de más.
public class Sacrificio
{
    private LinkedList<CartaMonstruo> cartasASacrificar;

    public Sacrificio()
    {
        this.cartasASacrificar = new LinkedList<>();
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
        cartaMonstruo = cartasASacrificar.getLast();
        cartasASacrificar.removeLast();

        return cartaMonstruo;
    }

    public void removerMonstruo(String nombreCarta) {

        if (cartasASacrificar.isEmpty()) {

            throw new NoHayCartasParaSacrificarError();
        }

        
        int indiceCartaARemover = this.cartasASacrificar.indexOf(cartaMonstruo);
        this.cartasASacrificar.remove(indiceCartaARemover);
    }

	public int cantidadDeSacrificios() {

        return this.cartasASacrificar.size();
	}

    public Iterator<CartaMonstruo> getIteratorCartasASacrificar(){

        return cartasASacrificar.iterator();
	}
}

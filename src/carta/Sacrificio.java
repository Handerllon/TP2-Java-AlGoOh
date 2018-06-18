package carta;

import carta.monstruo.CartaMonstruo;

import java.util.Stack;

// TODO: validar la cantidad de cartas que voy poniendo. Puede lanzar excepciones si pido de más.
public class Sacrificio
{
    private Stack<CartaMonstruo> cartasASacrificar;

    public Sacrificio()
    {
        this.cartasASacrificar = new Stack<CartaMonstruo>();
    }

    public void agregar(CartaMonstruo carta)
    {
        this.cartasASacrificar.push(carta);
    }

    public CartaMonstruo obtenerMonstruo()
    {
        return cartasASacrificar.pop();
    }
}

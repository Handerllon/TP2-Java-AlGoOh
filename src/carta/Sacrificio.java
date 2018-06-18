package carta;

import java.util.Stack;

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
        return cartasASacrificar.pop();
    }
}

package Modelo.carta;

import Modelo.carta.excepciones.NoHayCartasParaSacrificarError;

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

        if (cartasASacrificar.isEmpty())
            throw new NoHayCartasParaSacrificarError();

        CartaMonstruo cartaMonstruo;
        cartaMonstruo = cartasASacrificar.getLast();
        cartasASacrificar.removeLast();

        return cartaMonstruo;
    }

    public CartaMonstruo getMonstruo(String nombreCarta)
    {

        if (cartasASacrificar.isEmpty())
            throw new NoHayCartasParaSacrificarError();

        Iterator<CartaMonstruo> iterador = this.getIterador();
        while (iterador.hasNext())
        {
            CartaMonstruo cartaActual = iterador.next();
            if (cartaActual.obtenerNombre() == nombreCarta)
            {
                return this.cartasASacrificar.remove(this.cartasASacrificar.indexOf(cartaActual));
            }
        }

        return null;
    }

    public int cantidadSacrificiosDe(String nombreCarta)
    {
        int cantidadSacrificios = 0;
        Iterator<CartaMonstruo> iterador = this.getIterador();

        while (iterador.hasNext())
        {
            if (iterador.next().obtenerNombre() == nombreCarta)
            {
                cantidadSacrificios++;
            }
        }

        return cantidadSacrificios;
    }

    public Iterator<CartaMonstruo> getIterador()
    {
        return cartasASacrificar.iterator();
    }
}

package Modelo.carta;

import Modelo.carta.excepciones.NoHayCartasParaSacrificarError;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.CartaMonstruoNula;

import java.util.Iterator;
import java.util.LinkedList;

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

        return cartasASacrificar.removeLast();
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

        return new CartaMonstruoNula();
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

package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class ManEaterBug extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 600;
    private static int PUNTOS_ATAQUE = 450;

    public ManEaterBug(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 2;
        this.nombre = "Man-Eater Bug";
    }

    public void atacarOponente(CartaMonstruo cartaOponente)
    {
        super.atacarOponente(cartaOponente);
    }

    public void recibirAtaque(CartaMonstruo cartaAtacante)
    {

        if (!this.orientacionArriba())
        {
            this.cambiarOrientacion();
            this.efecto(cartaAtacante);
        } else
        {
            super.recibirAtaque(cartaAtacante);
        }
    }

    public void efecto(CartaMonstruo cartaADestruir)
    {
        this.oponente.destruirMonstruo(cartaADestruir);
    }
}

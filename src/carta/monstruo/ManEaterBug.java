package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class ManEaterBug extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 600;
    private static int PUNTOS_ATAQUE = 450;

    public ManEaterBug(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente);
        this.estrellas = 2;
        this.nombre = "Man-Eater Bug";
    }

    public void atacarCarta(CartaMonstruo cartaOponente)
    {
        super.atacarCarta(cartaOponente);
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

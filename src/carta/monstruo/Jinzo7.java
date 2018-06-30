package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class Jinzo7 extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 400;
    private static int PUNTOS_ATAQUE = 500;

    public Jinzo7(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente);
        this.estrellas = 2;
        this.nombre = "Jinzo #7";
    }

    public void efecto()
    {
        this.oponente.disminuirPuntosVida(this.puntosAtaque);
    }
}

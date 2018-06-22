package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Efecto;

public class ManEaterBug extends CartaMonstruo implements Efecto
{
    private static int PUNTOS_DEFENSA = 600;
    private static int PUNTOS_ATAQUE = 450;

    public ManEaterBug(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE,jugador,oponente);
        this.estrellas = 2;
        this.nombre = "Man Eater Bug";
    }

	public void efecto() {

		
	}
}

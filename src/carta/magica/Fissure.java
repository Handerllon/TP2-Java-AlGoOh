package carta.magica;

import AlGoOh.Jugador;
import carta.CartaMagica;
import carta.CartaMonstruo;

public class Fissure extends CartaMagica
{
    public Fissure(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente);
        this.nombre = "Fissure";
    }

    public void efecto()
    {

        CartaMonstruo cartaADestruir = this.oponente.obtenerMonstruoConMenorAtaque();

        this.oponente.destruirMonstruo(cartaADestruir);
    }
}

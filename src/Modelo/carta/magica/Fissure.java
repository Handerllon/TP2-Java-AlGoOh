package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.CartaMagica;
import Modelo.carta.CartaMonstruo;

public class Fissure extends CartaMagica
{
    public Fissure(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
        this.nombre = "Fissure";
    }

    public void efecto()
    {

        CartaMonstruo cartaADestruir = this.oponente.obtenerMonstruoConMenorAtaque();

        this.oponente.destruirMonstruo(cartaADestruir);
    }
}

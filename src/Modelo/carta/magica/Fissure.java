package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

public class Fissure extends CartaMagica
{
    private static String rutaImagen = "resources/imagenes/magica/Fissure.png";

    public Fissure(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Fissure";
    }

    public void efecto()
    {

        CartaMonstruo cartaADestruir = this.oponente.obtenerMonstruoConMenorAtaque();

        this.oponente.destruirMonstruo(cartaADestruir);
    }
}

package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.CartaMonstruoNula;

public class Fissure extends CartaMagica
{
    private static String rutaImagen = "resources/imagenes/magica/Fissure.png";

    public Fissure(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Fissure";
    }

    protected void efectoParticular()
    {

        if (this.estaBocaArriba())
        {
            CartaMonstruo cartaADestruir = this.getOponente().getMonstruoConMenorAtaque();

            if (CartaMonstruoNula.getInstancia() != cartaADestruir)
            {
                this.getOponente().destruirCarta(cartaADestruir);
            }
        }
    }
}

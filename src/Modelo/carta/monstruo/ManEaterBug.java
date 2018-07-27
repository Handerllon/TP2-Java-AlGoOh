package Modelo.carta.monstruo;

import Modelo.Jugador;

public class ManEaterBug extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 600;
    private static int PUNTOS_ATAQUE = 450;
    private static String rutaImagen = "resources/imagenes/monstruo/ManEaterBug.jpg";

    public ManEaterBug(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 2;
        this.nombre = "Man-Eater Bug";
    }

    @Override
    public void recibirAtaque(CartaMonstruo atacante)
    {
        if (!this.estaBocaArriba())
        {
            this.cambiarOrientacion();
            this.efecto(atacante);
        } else // Ataque normal.
        {
            super.recibirAtaque(atacante);
        }
    }

    // Unicamente se ejecuta cuando la carta se da vuelta, y puede ser en la fase preparación o en batalla (si
    // la atacan.
    // TODO: ver cómo hacer para que se active cuando se da vuelta en la fase preparación, luego de que fue invocada.
    public void efecto(CartaMonstruo cartaADestruir)
    {
        cartaADestruir.getPropietario().destruirCarta(cartaADestruir);
    }
}

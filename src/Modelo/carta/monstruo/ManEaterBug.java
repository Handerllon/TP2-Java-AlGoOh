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

    public void recibirAtaque(CartaMonstruo cartaAtacante)
    {

        if (this.estaBocaArriba() == false)
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
        cartaADestruir.getPropietario().destruirMonstruo(cartaADestruir);
    }
}

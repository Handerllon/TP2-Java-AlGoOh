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
        // Tiene que comenzar boca arriba así no se activa el efecto cuando se la manda desde la mano al campo.
        this.orientacionArriba = true;
    }

    @Override
    public void cambiarOrientacion()
    {
        if (this.estaBocaAbajo())
        {
            this.orientacionArriba = true;
            if (!this.getPropietario().getOponente().getRegionMonstruos().estaVacia())
            {
                this.efecto(this.getPropietario().getOponente().getRegionMonstruos().getUltimaCartaEnEntrar());
            }
        } else
        {
            this.orientacionArriba = false;
        }

        notificarCambioDeOrientacionDeCarta();
    }

    @Override
    public void recibirAtaque(CartaMonstruo atacante)
    {
        if (this.estaBocaAbajo())
        {
            this.orientacionArriba = true;
            this.efecto(atacante);
            notificarCambioDeOrientacionDeCarta();
        } else // Ataque normal.
        {
            super.recibirAtaque(atacante);
        }
    }

    // Unicamente se ejecuta cuando la carta se da vuelta, y puede ser en la fase preparación o en batalla (si
    // la atacan.
    public void efecto(CartaMonstruo cartaADestruir)
    {
        cartaADestruir.getPropietario().destruirCarta(cartaADestruir);
    }

    public boolean tieneFlipEffect()
    {
        return true;
    }
}

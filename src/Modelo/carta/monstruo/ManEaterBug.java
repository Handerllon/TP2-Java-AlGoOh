package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.ataque.Ataque;

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
        this.setAtaque(new AtaqueManEaterBug());
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

    // Unicamente se ejecuta cuando la carta se da vuelta.
    public void efecto(CartaMonstruo cartaADestruir)
    {
        cartaADestruir.getPropietario().destruirCarta(cartaADestruir);
    }

    private class AtaqueManEaterBug implements Ataque
    {
        @Override
        public void ejecutar(CartaMonstruo atacante, CartaMonstruo atacada)
        {
            atacada.recibirAtaque(atacante);

            if (atacada.estaBocaAbajo())
            {
                atacada.cambiarOrientacion();
            }
        }

        @Override
        public void ejecutar(CartaMonstruo atacante)
        {
            atacante.getOponente().disminuirPuntosVida(atacante.getPuntosDeAtaque());
        }
    }
}

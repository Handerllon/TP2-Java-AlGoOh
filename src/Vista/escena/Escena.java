package Vista.escena;

import Modelo.carta.monstruo.CartaMonstruo;

public interface Escena
{
    Escena cambiarEscena();

    void mostrar();

    void actualizarDibujo();

    void finDeJuego();

    void playMedia();

    void stopMedia();

    void cerrar();

    void solicitarCartaAAtacar(CartaMonstruo cartaAtacante);
}

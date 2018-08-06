package Vista.escena;

import Modelo.carta.Carta;

public interface Escena
{
    Escena cambiarEscena();

    void mostrar();

    void actualizarDibujo();

    void finDeJuego();

    void playMedia();

    void stopMedia();

    void cerrar();

    void solicitarCartaAAtacar(Carta cartaAtacante);
}

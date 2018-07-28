package Vista.escena;

public interface Escena
{
    Escena cambiarEscena();

    void dibujarEscena();

    void actualizarEstado();

    boolean terminoElJuego();

    void finDeJuego();

    void playMedia();

    void stopMedia();

    void cerrar();
}

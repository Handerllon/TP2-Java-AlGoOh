package Vista;

public interface EscenaVista
{
    EscenaVista cambiarEscena();

    void dibujarEscena();

    void actualizarEstado();

    boolean terminoElJuego();

    void finDeJuego();

    void playMedia();

    void stopMedia();

    void cerrar();
}

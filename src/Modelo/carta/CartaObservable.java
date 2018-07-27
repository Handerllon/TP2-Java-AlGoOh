package Modelo.carta;

public interface CartaObservable
{
    void agregarObsevador(ObservadorDeCarta observador);

    void quitarObservador(ObservadorDeCarta observador);

    void notificarObservadores();

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
}

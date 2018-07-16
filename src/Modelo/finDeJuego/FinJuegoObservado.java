package Modelo.finDeJuego;

public interface FinJuegoObservado
{
    void agregarObsevador(ObservadorDeFinJuego observador);

    void quitarObservador(ObservadorDeFinJuego observador);

    void notificarObservadores(CausaFinJuego causaFinJuego);
}

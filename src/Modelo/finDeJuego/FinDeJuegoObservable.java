package Modelo.finDeJuego;

public interface FinDeJuegoObservable
{
    void agregarObsevador(ObservadorDeFinJuego observador);

    void quitarObservador(ObservadorDeFinJuego observador);

    void notificarObservadores(CausaFinJuego causaFinJuego);
}

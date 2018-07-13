package Modelo.finDeJuego;

public interface FinJuegoObservable
{
    void agregarObsevador(ObservadorFinJuego observador);

    void quitarObservador(ObservadorFinJuego observador);

    void notificarFinDeJuego(CausaFinJuego causaFinJuego);
}

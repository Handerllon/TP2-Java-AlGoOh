package Modelo.finDeJuego;

public interface FinJuegoObservable
{
    void agregarObsevadorFinDeJuego(ObservadorFinJuego observador);

    void quitarObservadorFinDeJuego(ObservadorFinJuego observador);

    void notificarFinDeJuego(CausaFinJuego causaFinJuego);
}

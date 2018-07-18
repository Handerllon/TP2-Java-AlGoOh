package Modelo.finDeJuego;

public interface FinDeJuegoObservable
{
    void agregarObsevadorFinDeJuego(ObservadorDeFinJuego observador);

    void quitarObservadorFinDeJuego(ObservadorDeFinJuego observador);

    void notificarFinDeJuego(CausaFinJuego causaFinJuego);
}

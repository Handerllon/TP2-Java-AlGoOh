package Modelo.observadores;

public interface ObservadorDeModelo
{
    void seTomoCartaDeMazo();

    void ingresoCartaAMano();

    void egresoCartaAMano();

    void ingresoCartaARegion();

    void egresoCartaARegion();

    void cambiaronLosPuntosDeVida();

    void cartaCambioDeOrientacion();

    void cartaCambioDeModo();
}

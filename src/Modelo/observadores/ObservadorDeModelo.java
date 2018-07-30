package Modelo.observadores;

public interface ObservadorDeModelo
{
    void huboCambios();

    void seTomoCartaDeMazo();

    void ingresoCartaAMano();

    void egresoCartaAMano();

    void ingresoCartaARegion();

    void egresoCartaARegion();

    void cambiaronLosPuntosDeVida();

    void cartaCambioDeOrientacion();

    void cartaCambioDeModo();
}

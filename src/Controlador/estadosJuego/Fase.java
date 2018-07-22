package Controlador.estadosJuego;

public interface Fase
{
    Fase avanzar();

    String nombre();

    boolean esFaseInicial();

    boolean esFasePreparacion();

    boolean esFaseAtaque();

    boolean esFaseTrampa();

    boolean esFaseFinal();
}

package Controlador.estadosJuego;

public interface Fase
{
    Fase avanzar();

    String nombre();

    boolean enFaseInicial();

    boolean esFasePreparacion();

    boolean esFaseAtaque();

    boolean esFaseTrampa();

    boolean esFaseFinal();
}

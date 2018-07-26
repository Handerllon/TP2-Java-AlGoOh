package Controlador.estadosJuego;

public interface Fase
{
    Fase avanzar();

    String getNombre();

    boolean esFaseInicial();

    boolean esFasePreparacion();

    boolean esFaseAtaque();

    boolean esFaseTrampa();

    boolean esFaseFinal();
}

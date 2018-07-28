package Modelo.observadores;

import Modelo.finDeJuego.CausaFinJuego;

public interface ObservadorDeFinJuego
{
    void seLlegoAFinDeJuego(CausaFinJuego causaFinJuego);
}

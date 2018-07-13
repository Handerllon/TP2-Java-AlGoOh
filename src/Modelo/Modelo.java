package Modelo;

import Vista.ObjectoObservador;

import java.util.ArrayList;

public class Modelo implements ObjectoObservado
{
    private Jugador jugador1;
    private Jugador jugador2;

    private ArrayList<ObjectoObservador> observadores = new ArrayList<>();

    public Modelo(String nombreJugador, String nombreOponente)
    {
        this.jugador1 = new Jugador(nombreJugador);
        this.jugador2 = new Jugador(nombreOponente);

        this.jugador1.establecerOponente(this.jugador2);
        this.jugador2.establecerOponente(this.jugador1);

        this.jugador1.crearMazo();
        this.jugador2.crearMazo();
    }

    @Override
    public Jugador obtenerJugador()
    {
        return this.jugador1;
    }

    @Override
    public Jugador obtenerOponente()
    {
        return this.jugador2;
    }

    @Override
    public void agregarObsevador(ObjectoObservador observer)
    {
        this.observadores.add(observer);
    }

    @Override
    public void quitarObservador(ObjectoObservador observer)
    {
        this.observadores.remove(observer);
    }

    @Override
    public void notificarObservadores()
    {

        for (int i = 0; i < this.observadores.size(); i++)
        {

            this.observadores.get(i).update();
        }
    }
}

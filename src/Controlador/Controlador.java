package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
import Modelo.Modelo;
import Vista.Vista;

public class Controlador
{
    private Modelo modelo;
    private Vista vista;
    private MaquinaTurnos maquinaTurnos;

    public Controlador(Modelo modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;

        this.maquinaTurnos = new MaquinaTurnos(this.modelo.obtenerJugador(), this.modelo.obtenerOponente());
        //this.comenzarJuego();
    }

    public void comenzarJuego()
    {
        int i = 1;
        while (i != 20)
        {
            this.maquinaTurnos.jugar();
            i++;
        }
    }
}

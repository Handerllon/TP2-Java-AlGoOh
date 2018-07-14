package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
import Modelo.Modelo;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.ObservadorFinJuego;
import Vista.Vista;

public class Controlador implements ObservadorFinJuego
{
    private Modelo modelo;
    private Vista vista;
    private MaquinaTurnos maquinaTurnos;

    public Controlador(Modelo modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;

        this.modelo.agregarObsevadorFinDeJuego(this);

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

    @Override
    public void finDeJuego(CausaFinJuego causaFinJuego)
    {
        // TODO: implementar la notificación en la Vista para que muestre un cartel al cliente. Algo como:
        // vista.notificarFinDeJuego(causaFinJuego);
        System.out.println("Fin de Juego debido a " + causaFinJuego.obtenerCausa() + " causado por el jugador " +
                causaFinJuego.obtenerNombreJugador());
        System.exit(0);
    }
}

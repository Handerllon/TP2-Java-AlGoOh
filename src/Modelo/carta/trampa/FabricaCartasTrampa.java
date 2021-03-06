package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

public class FabricaCartasTrampa
{
    private Jugador jugador, oponente;

    public FabricaCartasTrampa(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> getNombres()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new MagicCylinder(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new Reinforcements(jugador, oponente).getNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaTrampa getCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            //Trampa.
            case "Magic Cylinder":
                return new MagicCylinder(jugador, oponente);
            case "Reinforcements":
                return new Reinforcements(jugador, oponente);
            default:
                throw new CartaInvalidaError();
        }
    }
}

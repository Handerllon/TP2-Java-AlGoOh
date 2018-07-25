package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

public class FabricaCartasCampo
{
    private Jugador jugador, oponente;

    public FabricaCartasCampo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> getNombres()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new Sogen(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new Wasteland(jugador, oponente).getNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaCampo getCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Campo.
            case "Sogen":
                return new Sogen(jugador, oponente);
            case "Wasteland":
                return new Wasteland(jugador, oponente);
            default:
                throw new CartaInvalidaError();
        }
    }
}

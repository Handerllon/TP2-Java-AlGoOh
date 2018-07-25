package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

public class FabricaCartasMagicas
{
    private Jugador jugador, oponente;

    public FabricaCartasMagicas(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> getNombres()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new DarkHole(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new Fissure(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new PotOfGreed(jugador, oponente).getNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaMagica getCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Magicas.
            case "Dark Hole":
                return new DarkHole(jugador, oponente);
            case "Fissure":
                return new Fissure(jugador, oponente);
            case "Pot of Greed":
                return new PotOfGreed(jugador, oponente);
            default:
                throw new CartaInvalidaError();
        }
    }
}

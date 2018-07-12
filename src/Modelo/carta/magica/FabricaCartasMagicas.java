package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.CartaMagica;
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

    public ArrayList<String> obtenerNombres()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new DarkHole(jugador, oponente,"resources/imagenes/magica/DarkHole.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new Fissure(jugador, oponente,"resources/imagenes/magica/Fissure.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new PotOfGreed(jugador, oponente,"resources/imagenes/magica/PotOfGreed.png").obtenerNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaMagica obtenerCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Magicas.
            case "Dark Hole":
                return new DarkHole(jugador, oponente,"resources/imagenes/magica/DarkHole.png");
            case "Fissure":
                return new Fissure(jugador, oponente,"resources/imagenes/magica/Fissure.png");
            case "Pot of Greed":
                return new PotOfGreed(jugador, oponente,"resources/imagenes/magica/PotOfGreed.png");
            default:
                throw new CartaInvalidaError();
        }
    }
}

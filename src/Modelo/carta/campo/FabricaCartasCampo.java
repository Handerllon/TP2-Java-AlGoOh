package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.CartaCampo;
import Modelo.carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

// TODO: Parametrizar los strings con las rutas.
public class FabricaCartasCampo
{
    private Jugador jugador, oponente;

    public FabricaCartasCampo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> obtenerNombres()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new Sogen(jugador, oponente, "resources/imagenes/campo/Sogen.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new Wasteland(jugador, oponente, "resources/imagenes/campo/Wasteland.png").obtenerNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaCampo obtenerCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Campo.
            case "Sogen":
                return new Sogen(jugador, oponente, "resources/imagenes/campo/Sogen.png");
            case "Wasteland":
                return new Wasteland(jugador, oponente, "resources/imagenes/campo/Wasteland.png");
            default:
                throw new CartaInvalidaError();
        }
    }
}

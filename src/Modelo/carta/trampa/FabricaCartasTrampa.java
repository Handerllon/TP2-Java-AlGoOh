package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.CartaTrampa;
import Modelo.carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

// TODO: Parametrizar los strings con las rutas.
public class FabricaCartasTrampa
{
    private Jugador jugador, oponente;

    public FabricaCartasTrampa(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> obtenerNombres()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new MagicCylinder(jugador, oponente, "resources/imagenes/trampa/MagicCylinder.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new Reinforcements(jugador, oponente, "resources/imagenes/trampa/Reinforcements.jpg").obtenerNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaTrampa obtenerCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            //Trampa.
            case "Magic Cylinder":
                return new MagicCylinder(jugador, oponente, "resources/imagenes/trampa/MagicCylinder.png");
            case "Reinforcements":
                return new Reinforcements(jugador, oponente, "resources/imagenes/trampa/Reinforcements.jpg");
            default:
                throw new CartaInvalidaError();
        }
    }
}

package carta.trampa;

import AlGoOh.Jugador;
import carta.CartaTrampa;
import carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

public class FabricaCartasTrampa {

    private Jugador jugador, oponente;

    public FabricaCartasTrampa(Jugador jugador, Jugador oponente) {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> obtenerNombres() {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new MagicCylinder(jugador, oponente).obtenerNombre();
        nombres.add(nombre);
        nombre = new Reinforcements(jugador, oponente).obtenerNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaTrampa obtenerCarta(String nombreCarta) {
        switch (nombreCarta) {
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

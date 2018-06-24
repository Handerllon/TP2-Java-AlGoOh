package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;
import carta.excepciones.NoHayTresDragonesBlancosParaSacrificioError;

import java.util.Iterator;

public class BlueEyesUltimateDragon extends CartaMonstruo {
    private static int PUNTOS_DEFENSA = 3800;
    private static int PUNTOS_ATAQUE = 4500;

    public BlueEyesUltimateDragon(Jugador jugador, Jugador oponente) {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente);
        this.estrellas = 12;
        this.nombre = "Blue-Eyes Ultimate Dragon";
    }


    public void invocar(Sacrificio sacrificio) {
        Iterator<CartaMonstruo> iterador = sacrificio.getIteratorCartasASacrificar();


        if (this.cantidadDragonesBlancosOjosAzules(sacrificio) == 3) {

            CartaMonstruo cartaActual = null;

            while (iterador.hasNext()) {
                cartaActual = iterador.next();
                // TODO: hay que crear un metodo que me devuelva la carta que yo quiero, y no la última.
                if (cartaActual.obtenerNombre() == "Blue-Eyes White Dragon") {
                    this.jugador.destruirMonstruo(sacrificio.getMonstruo());
                }
            }

        } else
            throw new NoHayTresDragonesBlancosParaSacrificioError();


        this.jugador.jugarCarta(this);
    }

    private int cantidadDragonesBlancosOjosAzules(Sacrificio sacrificio) {
        int cantidadDragonesBlancosDeOjosAzules = 0;

        Iterator<CartaMonstruo> iterador = sacrificio.getIteratorCartasASacrificar();

        while (iterador.hasNext()) {
            if (iterador.next().obtenerNombre() == "Blue-Eyes White Dragon") {
                cantidadDragonesBlancosDeOjosAzules++;
            }
        }

        return cantidadDragonesBlancosDeOjosAzules;
    }
}

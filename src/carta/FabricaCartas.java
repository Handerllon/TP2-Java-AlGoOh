package carta;

import AlGoOh.Jugador;
import carta.campo.FabricaCartasCampo;
import carta.magica.FabricaCartasMagicas;
import carta.monstruo.FabricaCartasMonstruo;
import carta.trampa.FabricaCartasTrampa;

import java.util.ArrayList;

public class FabricaCartas {
    private FabricaCartasCampo fabricaCartasCampo;
    private FabricaCartasMagicas fabricaCartasMagicas;
    private FabricaCartasMonstruo fabricaCartasMonstruo;
    private FabricaCartasTrampa fabricaCartasTrampa;

    public FabricaCartas(Jugador jugador, Jugador oponente) {
        this.fabricaCartasCampo = new FabricaCartasCampo(jugador, oponente);
        this.fabricaCartasMagicas = new FabricaCartasMagicas(jugador, oponente);
        this.fabricaCartasMonstruo = new FabricaCartasMonstruo(jugador, oponente);
        this.fabricaCartasTrampa = new FabricaCartasTrampa(jugador, oponente);
    }

    public CartaCampo crearCartaCampo(String nombre) {
        return this.fabricaCartasCampo.obtenerCarta(nombre);

    }

    public CartaMagica crearCartaMagica(String nombre) {
        return this.fabricaCartasMagicas.obtenerCarta(nombre);
    }

    public CartaMonstruo crearCartaMonstruo(String nombre) {
        return this.fabricaCartasMonstruo.obtenerCarta(nombre);
    }

    public CartaTrampa crearCartaTrampa(String nombre) {
        return this.fabricaCartasTrampa.obtenerCarta(nombre);
    }

    public ArrayList<String> obtenerNombresCartasCampo() {
        return this.fabricaCartasCampo.obtenerNombres();
    }

    public ArrayList<String> obtenerNombresCartasMagicas() {
        return this.fabricaCartasMagicas.obtenerNombres();
    }

    public ArrayList<String> obtenerNombresCartasTrampa() {
        return this.fabricaCartasTrampa.obtenerNombres();
    }

    public ArrayList<String> obtenerNombresCartasMonstruosNormales() {
        return this.fabricaCartasMonstruo.obtenerNombresMonstruosNormales();
    }

    public ArrayList<String> obtenerNombresCartasMonstruosNoNormales() {
        return this.fabricaCartasMonstruo.obtenerNombresMonstruosNoNormales();
    }
}
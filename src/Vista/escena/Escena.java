package Vista.escena;

import Modelo.carta.monstruo.CartaMonstruo;
import javafx.scene.layout.GridPane;

public interface Escena
{
    Escena cambiarEscena();

    void mostrar();

    void actualizarEstado();

    boolean terminoElJuego();

    void finDeJuego();

    void playMedia();

    void stopMedia();

    void cerrar();

    void mostrarJugadorActual();

    void mostrarFaseActual();

    GridPane getGridPaneEscena();

    void solicitarCartaAAtacar(CartaMonstruo cartaAtacante);
}

package Vista;

import Controlador.IControlador;
import Modelo.ModeloObservable;
import Modelo.ObservadorDeModelo;
import Modelo.carta.Sacrificio;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.escenas.EscenaBienvenida;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

;

public class Vista implements ObservadorDeModelo
{
    private static ModeloObservable modelo;
    private static IControlador controlador;
    private static double RESOLUCION_HORIZONTAL;
    private static double RESOLUCION_VERTICAL;
    private EscenaVista escenaVista;
    private Stage primaryStage;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public Vista(ModeloObservable modelo, IControlador controlador, Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.RESOLUCION_HORIZONTAL = Screen.getPrimary().getVisualBounds().getWidth();
        this.RESOLUCION_VERTICAL = Screen.getPrimary().getVisualBounds().getHeight();

        this.modelo = modelo;
        this.modelo.agregarObsevador(this);

        this.controlador = controlador;

        this.escenaVista = EscenaBienvenida.getInstancia(this.modelo, this);
    }

    // --------------------------------------------------------------------
    // Ejecucion.
    // --------------------------------------------------------------------

    public void mostrar()
    {
        this.escenaVista.dibujarEscena();
    }

    public void setProximaEscena(EscenaVista escenaVista)
    {
        this.escenaVista = escenaVista;
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------
    public Stage getPrimaryStage()
    {
        return this.primaryStage;
    }

    public IControlador getControlador()
    {
        return this.controlador;
    }

    public double getResolucionHorizontal()
    {
        return this.RESOLUCION_HORIZONTAL;
    }

    public double getResolucionVertical()
    {
        return this.RESOLUCION_VERTICAL;
    }

    public ModeloObservable getModelo()
    {
        return this.modelo;
    }

    public void mostrarJugadorActual()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Turno");
        alert.setHeaderText(null);
        alert.setContentText("Turno del jugador " + this.controlador.getNombreJugadorActual());
        alert.showAndWait();
    }

    public void mostrarFaseActual()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Turno");
        alert.setHeaderText(null);
        alert.setContentText("Fase: " + this.controlador.getNombreFaseActual());
        alert.showAndWait();
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void actualizar()
    {
        this.escenaVista.actualizarEstado();
    }

    // --------------------------------------------------------------------
    // Métodos de terminación.
    // --------------------------------------------------------------------
    public void finDeJuego()
    {
        this.escenaVista.finDeJuego();
        this.escenaVista.dibujarEscena();
    }

    public void confirmarSalirPrograma()
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar AlGoOh");
        alert.setHeaderText("Está seguro que desea salir?");
        alert.setContentText("Confirmación");

        ButtonType buttonTypeOne = new ButtonType("Si");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne)
        {
            this.getControlador().cerrarPrograma();
        } else
        {
            // El usuario cerro la ventana o apretó NO.
        }
    }

    public void cerrar()
    {
        this.escenaVista.cerrar();
    }

    public Sacrificio pedirSacrificios()
    {
        // TODO.
        return null;
    }

    public CartaMonstruo solicitarCartaAAtacar()
    {
        // TODO.
        return null;
    }
}

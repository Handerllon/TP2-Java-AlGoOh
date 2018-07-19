package Vista;

import Controlador.Controlador;
import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import Vista.escenas.EscenaBienvenida;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class Vista implements ObservadorDeModelo
{
    private static Modelo modelo;
    private static Controlador controlador;
    private static double RESOLUCION_HORIZONTAL;
    private static double RESOLUCION_VERTICAL;
    private EscenaVista escenaVista;
    private Stage primaryStage;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public Vista(Modelo modelo, Controlador controlador, Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.RESOLUCION_HORIZONTAL = Screen.getPrimary().getVisualBounds().getWidth();
        this.RESOLUCION_VERTICAL = Screen.getPrimary().getVisualBounds().getHeight();

        this.modelo = modelo;
        this.modelo.agregarObsevador(this);

        this.controlador = controlador;

        this.escenaVista = EscenaBienvenida.obtenerInstancia(this.modelo, this);
    }

    // --------------------------------------------------------------------
    // Ejecucion.
    // --------------------------------------------------------------------

    public void mostrar()
    {
        this.escenaVista.dibujarEscena();
    }

    public void establecerProximaEscena(EscenaVista escenaVista)
    {
        this.escenaVista = escenaVista;
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------
    public Stage obtenerPrimaryStage()
    {
        return this.primaryStage;
    }

    public Controlador obtenerControlador()
    {
        return this.controlador;
    }

    public double obtenerResolucionHorizontal()
    {
        return this.RESOLUCION_HORIZONTAL;
    }

    public double obtenerResolucionVertical()
    {
        return this.RESOLUCION_VERTICAL;
    }

    public Modelo obtenerModelo()
    {
        return this.modelo;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void actualizarEstado()
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
            this.obtenerControlador().cerrarPrograma();
        } else
        {
            // El usuario cerro la ventana o apretó NO.
        }
    }

    public void cerrar()
    {
        this.escenaVista.cerrar();
    }
}

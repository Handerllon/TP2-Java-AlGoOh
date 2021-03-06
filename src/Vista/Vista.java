package Vista;

import Controlador.ControladorInterfaz;
import Controlador.excepciones.*;
import Modelo.ModeloObservable;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.escena.Escena;
import Vista.escena.EscenaBienvenida;
import Vista.estadosJuego.ErroresVista;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class Vista
{
    private static ModeloObservable modelo;
    private static ControladorInterfaz controlador;
    private static double RESOLUCION_HORIZONTAL;
    private static double RESOLUCION_VERTICAL;
    private Escena escena;
    private Stage primaryStage;
    private ErroresVista erroresVista;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public Vista(ModeloObservable modelo, ControladorInterfaz controlador, Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.RESOLUCION_HORIZONTAL = Screen.getPrimary().getVisualBounds().getWidth();
        this.RESOLUCION_VERTICAL = Screen.getPrimary().getVisualBounds().getHeight();

        this.modelo = modelo;

        this.controlador = controlador;

        this.escena = EscenaBienvenida.getInstancia(this.modelo, this);

        this.erroresVista = new ErroresVista();
    }

    // --------------------------------------------------------------------
    // Ejecucion.
    // --------------------------------------------------------------------

    public void mostrar()
    {
        this.escena.mostrar();
    }

    public void setProximaEscena(Escena escena)
    {
        this.escena = escena;
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------
    public Stage getPrimaryStage()
    {
        return this.primaryStage;
    }

    public ModeloObservable getModelo()
    {
        return this.modelo;
    }

    public ControladorInterfaz getControlador()
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

    public void actualizarDibujo()
    {
        this.escena.actualizarDibujo();
    }

    public void solicitarCartaAAtacar(CartaMonstruo cartaAtacante)
    {
        this.escena.solicitarCartaAAtacar(cartaAtacante);
    }

    // --------------------------------------------------------------------
    // Métodos de terminación.
    // --------------------------------------------------------------------
    public void finDeJuego()
    {
        this.escena.finDeJuego();
        this.escena.mostrar();
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
        this.escena.cerrar();
    }

    // -------------------------------
    // Avisos de advertencias.
    // -------------------------------
    public void mostrarError(NoSePuedeTomarCartaError error)
    {
        erroresVista.mostrarError(error);
    }

    public void mostrarError(NoSePuedeEnviarCartaMonstruoARegionError error)
    {
        erroresVista.mostrarError(error);
    }

    public void mostrarError(NoSePuedeEnviarMyTARegionError error)
    {
        erroresVista.mostrarError(error);
    }

    public void mostrarError(NoSePuedeUsarMyTError error)
    {
        erroresVista.mostrarError(error);
    }

    public void mostrarError(NoSePuedeEnviarARegionCampoError error)
    {
        erroresVista.mostrarError(error);
    }

    public void mostrarError(NoSePuedeCambiarOrientacionError error)
    {
        erroresVista.mostrarError(error);
    }

    public void mostrarError(NoSePuedeAtacarError error)
    {
        erroresVista.mostrarError(error);
    }
}

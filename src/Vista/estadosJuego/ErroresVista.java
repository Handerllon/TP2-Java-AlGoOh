package Vista.estadosJuego;

import Controlador.excepciones.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErroresVista
{
    public ErroresVista()
    {

    }

    public void mostrarError(NoSePuedeTomarCartaError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : No se puede tomar carta.\n" + error
                .getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarCartaMonstruoARegionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : La carta monstruo no puede ser envíada a la región.\n" +
                "monstruo. " + error
                .getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarMyTARegionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : La carta MyT no puede ser envíada a la región MyT.\n"
                + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeUsarMyTError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : La carta elegida no puede ser utilizada.\n" + error
                .getEstadoVerificador().getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarARegionCampoError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : La carta campo no puede ser envíada a la región campo" +
                ".\n" + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeCambiarOrientacionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : No se puede cambiar la orientacion de la carta " +
                "elegida.\n" + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeAtacarError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " : No se puede atacar.\n" + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }
}

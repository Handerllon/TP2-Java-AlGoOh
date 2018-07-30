package Vista.estadosJuego;

import Controlador.excepciones.*;
import Modelo.carta.excepciones.ManoLlena;
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
        alert.setContentText(error.getNombreResponsable() + " :No se puede tomar carta. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarCartaMonstruoARegionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta monstruo no puede ser envíada a la región monstruo. " + error
                .getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarMyTARegionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta MyT no puede ser envíada a la región MyT. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeUsarMyTError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta elegida no puede ser utilizada. " + error.getEstadoVerificador().getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarARegionCampoError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta campo no puede ser envíada a la región campo. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeCambiarOrientacionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :No se puede cambiar la orientacion de la carta elegida. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeAtacarError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :No se puede atacar. " + error.getEstadoVerificador().getNombre());

        alert.showAndWait();
    }

    public void avisoManoLlena(ManoLlena aviso)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(aviso.getNombreResponsable() + " :La mano estaba llena y se descartó una carta al azar. ");

        alert.showAndWait();
    }
}

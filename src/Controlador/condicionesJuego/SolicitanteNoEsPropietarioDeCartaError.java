package Controlador.condicionesJuego;

public class SolicitanteNoEsPropietarioDeCartaError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "El solicitante no es propietario de la carta";
    }
}

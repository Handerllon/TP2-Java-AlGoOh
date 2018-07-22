package Controlador.excepciones;

public class NoEsFaseInicial implements Razon
{
    public NoEsFaseInicial()
    {

    }

    @Override
    public String nombre()
    {
        return "No es fase inicial";
    }
}

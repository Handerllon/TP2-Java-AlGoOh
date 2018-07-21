package Controlador.estadosJuego;

public final class FaseNula implements Fase
{
    private static final String nombreFase = "";
    private static FaseNula instancia = null;
    private final MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FaseNula(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FaseNula obtenerInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FaseNula(maquinaTurnos);
        }
        return instancia;
    }

    public FaseNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // --------------------------------------------------------------------
    // Métodos de fase.
    // --------------------------------------------------------------------
    @Override
    public Fase avanzar()
    {
        return FaseNula.obtenerInstancia(this.maquinaTurnos);
    }

    @Override
    public String nombre()
    {
        return nombreFase;
    }

    @Override
    public boolean enFaseInicial()
    {
        return false;
    }

    @Override
    public boolean esFasePreparacion()
    {
        return false;
    }

    @Override
    public boolean esFaseAtaque()
    {
        return false;
    }

    @Override
    public boolean esFaseTrampa()
    {
        return false;
    }

    @Override
    public boolean esFaseFinal()
    {
        return false;
    }
}

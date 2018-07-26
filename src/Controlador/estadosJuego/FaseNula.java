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

    public static FaseNula getInstancia(MaquinaTurnos maquinaTurnos)
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
        return FaseNula.getInstancia(this.maquinaTurnos);
    }

    @Override
    public Fase retroceder()
    {
        return FaseNula.getInstancia(this.maquinaTurnos);
    }

    @Override
    public String getNombre()
    {
        return nombreFase;
    }

    @Override
    public boolean esFaseInicial()
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

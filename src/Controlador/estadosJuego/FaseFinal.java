package Controlador.estadosJuego;

public final class FaseFinal implements Fase
{
    private static String nombreFase = "Final";
    private static FaseFinal instancia = null;
    private MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FaseFinal(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FaseFinal getInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FaseFinal(maquinaTurnos);
        }
        return instancia;
    }

    public FaseFinal clone() throws CloneNotSupportedException
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
        return FaseAtaque.getInstancia(this.maquinaTurnos);
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
        return true;
    }
}

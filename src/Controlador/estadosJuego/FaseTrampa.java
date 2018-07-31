package Controlador.estadosJuego;

public final class FaseTrampa implements Fase
{
    private static String nombreFase = "Trampas";
    private static FaseTrampa instancia = null;
    private MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FaseTrampa(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FaseTrampa getInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FaseTrampa(maquinaTurnos);
        }
        return instancia;
    }

    public FaseTrampa clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // --------------------------------------------------------------------
    // Métodos de fase.
    // --------------------------------------------------------------------
    @Override
    public Fase avanzar()
    {
        return retroceder();
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
        return true;
    }

    @Override
    public boolean esFaseFinal()
    {
        return false;
    }
}

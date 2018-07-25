package Controlador.estadosJuego;

public final class FaseTrampas implements Fase
{
    private static final String nombreFase = "Trampas";
    private static FaseTrampas instancia = null;
    private final MaquinaTurnos maquinaTurnos;
    private boolean noHayMasAtaques = true;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FaseTrampas(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FaseTrampas getInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FaseTrampas(maquinaTurnos);
        }
        return instancia;
    }

    public FaseTrampas clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // --------------------------------------------------------------------
    // Métodos de fase.
    // --------------------------------------------------------------------
    @Override
    public Fase avanzar()
    {
        if (noHayMasAtaques == true)
        {
            return FaseFinal.getInstancia(this.maquinaTurnos);
        } else
        {
            return FaseAtaque.getInstancia(this.maquinaTurnos);
        }
    }

    @Override
    public String nombre()
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

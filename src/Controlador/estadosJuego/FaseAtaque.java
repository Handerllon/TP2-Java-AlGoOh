package Controlador.estadosJuego;

public final class FaseAtaque implements Fase
{
    private static final String nombreFase = "Ataque";
    private static FaseAtaque instancia = null;
    private final MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FaseAtaque(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FaseAtaque getInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FaseAtaque(maquinaTurnos);
        }
        return instancia;
    }

    public FaseAtaque clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // --------------------------------------------------------------------
    // Métodos de fase.
    // --------------------------------------------------------------------
    @Override
    public Fase avanzar()
    {
        return FaseFinal.getInstancia(this.maquinaTurnos);
    }

    @Override
    public Fase retroceder()
    {
        return FasePreparacion.getInstancia(this.maquinaTurnos);
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
        return true;
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

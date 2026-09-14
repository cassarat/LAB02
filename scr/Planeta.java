public class Planeta {
    private String codigo;
    private String nombre;
    private double distancia;
    private double temperatura;
    private int nivelHabitabilidad;

    public Planeta(String codigo, String nombre, double distancia, double temperatura, int nivelHabitabilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        setDistancia(distancia); // Valida la regla 
        this.temperatura = temperatura;
        setNivelHabitabilidad(nivelHabitabilidad); // Valida la regla 
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        if (distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor que 0.");
        }
        this.distancia = distancia;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getNivelHabitabilidad() {
        return nivelHabitabilidad;
    }
        //la excepción de habitabilidad entre 0 y 100 
    public void setNivelHabitabilidad(int nivelHabitabilidad) {
        if (nivelHabitabilidad < 0 || nivelHabitabilidad > 100) {
            throw new IllegalArgumentException("El nivel de habitabilidad debe encontrarse entre 0 y 100.");
        }
        this.nivelHabitabilidad = nivelHabitabilidad;
    }
}
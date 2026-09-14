public class Modulo {
    private String codigo;
    private String nombre;
    private String tipo;
    private double consumoEnergia;
    private String estado;

    public Modulo(String codigo, String nombre, String tipo, double consumoEnergia, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        setConsumoEnergia(consumoEnergia); // nos ayuda a evitar duplicar el codigo
        this.estado = estado;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }
        //la excepción, de cuando no cumple  el minimo de consumo de enrgía 
    public void setConsumoEnergia(double consumoEnergia) {
        if (consumoEnergia <= 0) {
            throw new IllegalArgumentException("El consumo de energía debe ser mayor que 0.");
        }
        this.consumoEnergia = consumoEnergia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
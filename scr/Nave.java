// este archivo actua como el cerebro de todo el programa 
//se administra el arreglo (memoria fija)
// ArrayList (memoria dinamica)
import java.util.ArrayList;

public class Nave {
    private String nombre;
    private String codigoIdentificacion;
    private String nombreComandante;
    private Modulo[] modulos;
    private ArrayList<Planeta> planetas;

    public Nave(String nombre, String codigoIdentificacion, String nombreComandante) {
        this.nombre = nombre;
        this.codigoIdentificacion = codigoIdentificacion;
        this.nombreComandante = nombreComandante;
        this.modulos = new Modulo[5]; // arreglo basico de cinco espacios
        this.planetas = new ArrayList<>(); // ArrayList para cantidad que no se conoce
    }

    // metodos para administrar los modulos 

    public void instalarModulo(int posicion, Modulo modulo) {
        if (posicion < 0 || posicion >= modulos.length) {
            throw new IllegalArgumentException("La posición indicada no se encuentra dentro de los límites del arreglo.");
        }
        if (modulos[posicion] != null) {
            throw new IllegalArgumentException("La posición seleccionada ya se encuentra ocupada.");
        }
        if (modulo == null) {
            throw new IllegalArgumentException("El módulo posee información inválida.");
        }
        modulos[posicion] = modulo;
    }

    public void consultarModulos() {
        System.out.println("--- Módulos Instalados ---");
        boolean hayModulos = false;
        for (int i = 0; i < modulos.length; i++) {
            if (modulos[i] != null) { // Solo se muestran las posiciones que no tienen null
                System.out.println("Posición " + i + ": " + modulos[i].getNombre() + " (Código: " + modulos[i].getCodigo() + ")");
                hayModulos = true;
            }
        }
        if (!hayModulos) {
            System.out.println("No hay módulos instalados en la nave.");
        }
    }

    public Modulo consultarModulo(int posicion) {
        if (posicion < 0 || posicion >= modulos.length || modulos[posicion] == null) {
            return null; // El DriverProgram deberá informar al usuario si recibe null
        }
        return modulos[posicion];
    }

    public void modificarModulo(int posicion, double nuevoConsumo, String nuevoEstado) {
        if (posicion < 0 || posicion >= modulos.length || modulos[posicion] == null) {
            throw new IllegalArgumentException("No se puede modificar una posición inválida o que contenga null.");
        }
        // El setter de Modulo ya se encarga de validar que sea mayor a 0
        modulos[posicion].setConsumoEnergia(nuevoConsumo);
        modulos[posicion].setEstado(nuevoEstado);
    }

    public void retirarModulo(int posicion) {
        if (posicion < 0 || posicion >= modulos.length || modulos[posicion] == null) {
            throw new IllegalArgumentException("No se puede retirar de una posición inválida o vacía.");
        }
        modulos[posicion] = null; // La posición vuelve a contener null
    }

    //  metodos para administrar los planetas que son ArrayList

    public void registrarPlaneta(Planeta planeta) {
        for (Planeta p : planetas) {
            if (p.getCodigo().equals(planeta.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un planeta registrado con el código ingresado.");
            }
        }
        planetas.add(planeta);
    }

    public void consultarPlanetas() {
        System.out.println("--- Planetas Descubiertos ---");
        if (planetas.isEmpty()) {
            System.out.println("Todavía no existen planetas registrados.");
            return;
        }
        for (Planeta p : planetas) {
            System.out.println("Código: " + p.getCodigo() + " | Nombre: " + p.getNombre() + " | Habitabilidad: " + p.getNivelHabitabilidad());
        }
    }

    public Planeta buscarPlaneta(String codigo) {
        for (Planeta p : planetas) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null; // Retorna null si no lo encuentra
    }

    public void modificarPlaneta(String codigo, double nuevaDistancia, double nuevaTemperatura, int nuevoNivel) {
        Planeta p = buscarPlaneta(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El planeta no se encuentra registrado.");
        }
        // Los setters de Planeta ya validan las reglas del negocio
        p.setDistancia(nuevaDistancia);
        p.setTemperatura(nuevaTemperatura);
        p.setNivelHabitabilidad(nuevoNivel);
    }

    public void eliminarPlaneta(String codigo) {
        Planeta p = buscarPlaneta(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El planeta no se encuentra registrado.");
        }
        planetas.remove(p);
    }

    // metodos que hacen el calculo para el reporte

    public void mostrarReporteMision() {
        System.out.println("\n=== REPORTE DE MISIÓN ===");
        
        // Calculos de Modulos
        int instalados = 0;
        Modulo moduloMayorConsumo = null;
        
        for (Modulo m : modulos) {
            if (m != null) {
                instalados++;
                if (moduloMayorConsumo == null || m.getConsumoEnergia() > moduloMayorConsumo.getConsumoEnergia()) {
                    moduloMayorConsumo = m;
                }
            }
        }
        
        System.out.println("Cantidad de modulos instalados: " + instalados);
        System.out.println("Cantidad de espacios disponibles: " + (5 - instalados));
        if (moduloMayorConsumo != null) {
            System.out.println("Modulo con mayor consumo: " + moduloMayorConsumo.getNombre() + " (" + moduloMayorConsumo.getConsumoEnergia() + ")");
        }

        // Calculos de Planetas
        System.out.println("Cantidad de planetas descubiertos: " + planetas.size());
        
        if (!planetas.isEmpty()) { // Evita Calculos que requieran elementos si esta vacio
            Planeta planetaMayorHab = planetas.get(0);
            Planeta planetaMenorHab = planetas.get(0);
            double sumaHabitabilidad = 0;

            for (Planeta p : planetas) {
                if (p.getNivelHabitabilidad() > planetaMayorHab.getNivelHabitabilidad()) {
                    planetaMayorHab = p;
                }
                if (p.getNivelHabitabilidad() < planetaMenorHab.getNivelHabitabilidad()) {
                    planetaMenorHab = p;
                }
                sumaHabitabilidad += p.getNivelHabitabilidad();
            }

            double promedioHabitabilidad = sumaHabitabilidad / planetas.size();
            System.out.println("Planeta con mayor nivel de habitabilidad: " + planetaMayorHab.getNombre());
            System.out.println("Planeta con menor nivel de habitabilidad: " + planetaMenorHab.getNombre());
            System.out.println("Promedio del nivel de habitabilidad: " + promedioHabitabilidad);
        } else {
            System.out.println("No hay suficientes datos de planetas para calcular métricas de habitabilidad.");
        }
        System.out.println("=========================\n");
    }
}
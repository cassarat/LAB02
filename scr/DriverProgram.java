// menu cuenta con 13 opciones 
import java.util.InputMismatchException;
import java.util.Scanner;

public class DriverProgram {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Nave naveActual = null;
        int opcion = 0;

        System.out.println("¡Bienvenido al Sistema de Exploración Espacial!");

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Nueva nave");
            System.out.println("2. Instalar módulo");
            System.out.println("3. Consultar módulos");
            System.out.println("4. Consultar un módulo específico");
            System.out.println("5. Modificar módulo");
            System.out.println("6. Retirar módulo");
            System.out.println("7. Registrar planeta");
            System.out.println("8. Consultar planetas");
            System.out.println("9. Buscar planeta");
            System.out.println("10. Modificar planeta");
            System.out.println("11. Eliminar planeta");
            System.out.println("12. Mostrar reporte de misión");
            System.out.println("13. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer 

                // validar nave existente para las opciones de la 2 a la 12
                if (opcion >= 2 && opcion <= 12 && naveActual == null) {
                    System.out.println("Error: Primero debe registrar una 'Nueva nave' (Opción 1).");
                    continue; // Vuelve a mostrar el menú
                }

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre de la nave: ");
                        String nombreNave = teclado.nextLine();
                        System.out.print("Código de identificación: ");
                        String codNave = teclado.nextLine();
                        System.out.print("Nombre del comandante: ");
                        String comandante = teclado.nextLine();
                        
                        naveActual = new Nave(nombreNave, codNave, comandante);
                        System.out.println("¡Nave creada con éxito! Se ha iniciado una misión limpia.");
                        break;

                    case 2:
                        System.out.print("Posición en el arreglo (0-4): ");
                        int posInstalar = teclado.nextInt();
                        teclado.nextLine(); 
                        
                        System.out.print("Código del módulo: ");
                        String codMod = teclado.nextLine();
                        System.out.print("Nombre del módulo: ");
                        String nomMod = teclado.nextLine();
                        System.out.print("Tipo de módulo: ");
                        String tipoMod = teclado.nextLine();
                        System.out.print("Consumo de energía (mayor a 0): ");
                        double consumoMod = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.print("Estado (Ej: Activo, En espera): ");
                        String estMod = teclado.nextLine();
                        
                        Modulo nuevoModulo = new Modulo(codMod, nomMod, tipoMod, consumoMod, estMod);
                        naveActual.instalarModulo(posInstalar, nuevoModulo);
                        System.out.println("Módulo instalado correctamente.");
                        break;

                    case 3:
                        naveActual.consultarModulos();
                        break;

                    case 4:
                        System.out.print("Ingrese la posición a consultar (0-4): ");
                        int posConsulta = teclado.nextInt();
                        teclado.nextLine();
                        
                        Modulo modConsultado = naveActual.consultarModulo(posConsulta);
                        if (modConsultado == null) {
                            System.out.println("La posición indicada no es válida o se encuentra vacía.");
                        } else {
                            System.out.println("Módulo en posición " + posConsulta + ": " + modConsultado.getNombre() + 
                                               " | Tipo: " + modConsultado.getTipo() + " | Consumo: " + modConsultado.getConsumoEnergia());
                        }
                        break;

                    case 5:
                        System.out.print("Posición del módulo a modificar (0-4): ");
                        int posMod = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("Nuevo consumo de energía: ");
                        double nuevoConsumo = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.print("Nuevo estado: ");
                        String nuevoEstado = teclado.nextLine();
                        
                        naveActual.modificarModulo(posMod, nuevoConsumo, nuevoEstado);
                        System.out.println("Módulo modificado exitosamente.");
                        break;

                    case 6:
                        System.out.print("Posición del módulo a retirar (0-4): ");
                        int posRetirar = teclado.nextInt();
                        teclado.nextLine();
                        
                        naveActual.retirarModulo(posRetirar);
                        System.out.println("El módulo ha sido retirado de la nave.");
                        break;

                    case 7:
                        System.out.print("Código del planeta: ");
                        String codPlaneta = teclado.nextLine();
                        System.out.print("Nombre del planeta: ");
                        String nomPlaneta = teclado.nextLine();
                        System.out.print("Distancia desde la nave (mayor a 0): ");
                        double distPlaneta = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.print("Temperatura: ");
                        double tempPlaneta = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.print("Nivel de habitabilidad (0-100): ");
                        int habPlaneta = teclado.nextInt();
                        teclado.nextLine();
                        
                        Planeta nuevoPlaneta = new Planeta(codPlaneta, nomPlaneta, distPlaneta, tempPlaneta, habPlaneta);
                        naveActual.registrarPlaneta(nuevoPlaneta);
                        System.out.println("Planeta registrado en la base de datos.");
                        break;

                    case 8:
                        naveActual.consultarPlanetas();
                        break;

                    case 9:
                        System.out.print("Ingrese el código del planeta a buscar: ");
                        String codBuscar = teclado.nextLine();
                        Planeta planetaBuscado = naveActual.buscarPlaneta(codBuscar);
                        
                        if (planetaBuscado == null) {
                            System.out.println("No se encontró ningún planeta con ese código.");
                        } else {
                            System.out.println("Planeta encontrado: " + planetaBuscado.getNombre() + " | Distancia: " + planetaBuscado.getDistancia());
                        }
                        break;

                    case 10:
                        System.out.print("Ingrese el código del planeta a modificar: ");
                        String codModPlaneta = teclado.nextLine();
                        System.out.print("Nueva distancia: ");
                        double nuevaDist = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.print("Nueva temperatura: ");
                        double nuevaTemp = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.print("Nuevo nivel de habitabilidad (0-100): ");
                        int nuevoHab = teclado.nextInt();
                        teclado.nextLine();
                        
                        naveActual.modificarPlaneta(codModPlaneta, nuevaDist, nuevaTemp, nuevoHab);
                        System.out.println("Planeta modificado exitosamente.");
                        break;

                    case 11:
                        System.out.print("Ingrese el código del planeta a eliminar: ");
                        String codEliminar = teclado.nextLine();
                        naveActual.eliminarPlaneta(codEliminar);
                        System.out.println("Planeta eliminado correctamente.");
                        break;

                    case 12:
                        naveActual.mostrarReporteMision();
                        break;

                    case 13:
                        System.out.println("Finalizando sistema... ¡Buen viaje!");
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, seleccione un número del 1 al 13.");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Ha ingresado un tipo de dato incorrecto (ej. letras en lugar de números).");
                teclado.nextLine(); // Se limpia el buffer para evitar un ciclo infinito
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR DE VALIDACIÓN: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR INESPERADO: Ocurrió un problema durante la ejecución.");
            } finally {
                // Este bloque se ejecuta siempre, haya ocurrido un error o no, finalizando la operación actual.
                System.out.println("[Info] Ciclo de operación finalizado.");
            }

        } while (opcion != 13);

        teclado.close();
    }
}
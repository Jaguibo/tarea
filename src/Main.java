import java.util.Scanner;

public class Main {
    private static SistemaReservas sistema = SistemaReservas.getInstancia(); // Usamos Singleton
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Sistema de Reservas de Hotel ===");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar habitación");
            System.out.println("3. Realizar reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Listar habitaciones disponibles");
            System.out.println("6. Editar cliente");
            System.out.println("7. Mostrar estadísticas");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> agregarHabitacion();
                case 3 -> realizarReserva();
                case 4 -> cancelarReserva();
                case 5 -> listarHabitacionesDisponibles();
                case 6 -> editarCliente();
                case 7 -> sistema.mostrarEstadisticas();
                case 8 -> {
                    System.out.println("Gracias por usar el sistema de reservas.");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void agregarCliente() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, email);
        sistema.agregarCliente(cliente);
        System.out.println("Cliente agregado exitosamente.");
    }

    private static void agregarHabitacion() {
        System.out.print("Ingrese el número de la habitación: ");
        int numero = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el tipo de habitación (Simple/Doble/Suite): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese el precio base de la habitación: ");
        double precioBase = Double.parseDouble(scanner.nextLine());

        Habitacion habitacion = new Habitacion(numero, tipo, precioBase);
        sistema.agregarHabitacion(habitacion);
        System.out.println("Habitación agregada exitosamente.");
    }

    private static void realizarReserva() {
        System.out.print("Ingrese el email del cliente: ");
        String emailCliente = scanner.nextLine();

        System.out.print("Ingrese el número de la habitación: ");
        int numeroHabitacion = Integer.parseInt(scanner.nextLine());

        try {
            sistema.realizarReserva(emailCliente, numeroHabitacion);
            System.out.println("Reserva realizada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al realizar la reserva: " + e.getMessage());
        }
    }

    private static void cancelarReserva() {
        System.out.print("Ingrese el email del cliente: ");
        String emailCliente = scanner.nextLine();

        System.out.print("Ingrese el número de la habitación: ");
        int numeroHabitacion = Integer.parseInt(scanner.nextLine());

        try {
            sistema.cancelarReserva(emailCliente, numeroHabitacion);
            System.out.println("Reserva cancelada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al cancelar la reserva: " + e.getMessage());
        }
    }

    private static void listarHabitacionesDisponibles() {
        sistema.listarHabitacionesDisponibles();
    }

    private static void editarCliente() {
        System.out.print("Ingrese el email actual del cliente: ");
        String emailActual = scanner.nextLine();

        System.out.print("Ingrese el nuevo nombre del cliente: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Ingrese el nuevo email del cliente: ");
        String nuevoEmail = scanner.nextLine();

        try {
            sistema.editarCliente(emailActual, nuevoNombre, nuevoEmail);
            System.out.println("Cliente editado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al editar el cliente: " + e.getMessage());
        }
    }
}
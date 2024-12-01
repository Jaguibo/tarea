import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SistemaReservas {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Habitacion> habitaciones = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private static SistemaReservas instancia;

    private SistemaReservas() {}

    // Patrón Singleton
    public static SistemaReservas getInstancia() {
        if (instancia == null) {
            instancia = new SistemaReservas();
        }
        return instancia;
    }

    // Agregar cliente
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Agregar habitación
    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    // Realizar reserva
    public void realizarReserva(String emailCliente, int numeroHabitacion) throws Exception {
        Cliente cliente = obtenerClientePorEmail(emailCliente);
        Habitacion habitacion = obtenerHabitacionDisponible(numeroHabitacion);

        habitacion.reservar();
        Reserva reserva = new Reserva(cliente, habitacion);
        reservas.add(reserva);
    }

    // Cancelar reserva
    public void cancelarReserva(String emailCliente, int numeroHabitacion) throws Exception {
        Reserva reserva = obtenerReserva(emailCliente, numeroHabitacion);
        reservas.remove(reserva);
        reserva.habitacion.liberar();
    }

    // Listar habitaciones disponibles
    public void listarHabitacionesDisponibles() {
        List<Habitacion> disponibles = habitaciones.stream()
                .filter(Habitacion::esDisponible)
                .toList();

        if (disponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles.");
        } else {
            disponibles.forEach(System.out::println);
        }
    }

    // Editar cliente
    public void editarCliente(String emailActual, String nuevoNombre, String nuevoEmail) throws Exception {
        Cliente cliente = obtenerClientePorEmail(emailActual);
        cliente.setNombre(nuevoNombre);
        cliente.setEmail(nuevoEmail);
    }

    // Mostrar estadísticas
    public void mostrarEstadisticas() {
        long habitacionesOcupadas = habitaciones.stream().filter(h -> !h.esDisponible()).count();
        System.out.println("=== Estadísticas ===");
        System.out.println("Total de clientes registrados: " + clientes.size());
        System.out.println("Total de habitaciones ocupadas: " + habitacionesOcupadas);
        System.out.println("Total de reservas realizadas: " + reservas.size());
    }

    // Métodos auxiliares

    // Obtener cliente por email
    private Cliente obtenerClientePorEmail(String email) throws Exception {
        return clientes.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new Exception("Cliente no encontrado."));
    }

    // Obtener habitación disponible
    private Habitacion obtenerHabitacionDisponible(int numeroHabitacion) throws Exception {
        return habitaciones.stream()
                .filter(h -> h.getNumero() == numeroHabitacion && h.esDisponible())
                .findFirst()
                .orElseThrow(() -> new Exception("Habitación no disponible o no existe."));
    }

    // Obtener reserva
    private Reserva obtenerReserva(String emailCliente, int numeroHabitacion) throws Exception {
        return reservas.stream()
                .filter(r -> r.cliente.getEmail().equals(emailCliente) &&
                        r.habitacion.getNumero() == numeroHabitacion)
                .findFirst()
                .orElseThrow(() -> new Exception("Reserva no encontrada."));
    }
}

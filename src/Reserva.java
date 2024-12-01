public class Reserva {
    Cliente cliente;
    Habitacion habitacion;

    public Reserva(Cliente cliente, Habitacion habitacion) {
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "Reserva: " + cliente.getNombre() + " - " + habitacion;
    }
}

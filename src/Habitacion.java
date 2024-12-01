public class Habitacion {
    private int numero;
    private String tipo;
    private double precioBase;
    private boolean disponible;

    public Habitacion(int numero, String tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    public boolean esDisponible() {
        return disponible;
    }

    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("La habitación no está disponible.");
        }
        disponible = false;
    }

    public void liberar() {
        disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public String toString() {
        return "Habitación " + numero + " (" + tipo + ", $" + precioBase + ")";
    }
}

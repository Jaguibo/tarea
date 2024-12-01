public class Cliente {
    private String nombre;
    private String email;

    // Constructor para inicializar los atributos
    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getter para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener el email
    public String getEmail() {
        return email;
    }

    // Setter para modificar el email
    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para representar al cliente de manera legible
    @Override
    public String toString() {
        return "Cliente: " + nombre + " (Email: " + email + ")";
    }
}

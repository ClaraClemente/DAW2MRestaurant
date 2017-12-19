/*
 * clase auxiliar para consulta de cocineros
 */
package modelo;

/**
 *
 * @author mfontana
 */
public class RankingCocineroTO {
    String nombre;
    int cantidad;

    public RankingCocineroTO(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "{" + nombre + " - " + cantidad + '}';
    }
    
    
}

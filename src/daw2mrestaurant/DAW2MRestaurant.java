/*
 * Clase de TEST
 */
package daw2mrestaurant;

import dao.RestaurantDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cocinero;

/**
 *
 * @author mfontana
 */
public class DAW2MRestaurant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Necesitamos un objeto de tipo RestaurantDAO
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        System.out.println("Testeando conexión con la base de datos...");
        try {
            restaurantDAO.conectar();
            System.out.println("Establecida la conexión.");
            Cocinero c1 = new Cocinero("Cocinillas Pérez", "12345555", "Hombre", 30, 10, "Postres");
            System.out.println("Testeando insert cocinero " + c1.getNombre());
            try {
                restaurantDAO.insertarCocinero(c1);
                System.out.println("Cocinero dado de alta");
            } catch (SQLException ex) {
                System.out.println("Error al insertar: " + ex.getMessage());
            }
            Cocinero c2 = new Cocinero("Maria Castañera", "33333333", "Mujer", 23, 0, "Entrantes");
            System.out.println("Testeando insert cocinero " + c2.getNombre());
            try {
                restaurantDAO.insertarCocinero(c2);
                System.out.println("Cocinero dado de alta");
            } catch (SQLException ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
            System.out.println("Cerrando conexión con la base de datos");
            restaurantDAO.desconectar();
            System.out.println("Conexión cerrada.");
        } catch (SQLException ex) {
            System.out.println("Error al conectar / desconectar: " + ex.getMessage());
        }

    }

}

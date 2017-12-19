/*
 * Clase de TEST
 */
package daw2mrestaurant;

import dao.RestaurantDAO;
import excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cocinero;
import modelo.Plato;

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
        // Datos de test
        Cocinero c1 = new Cocinero("Cocinillas Pérez", "12345555", "Hombre", 30, 10, "Postres");
        Cocinero c2 = new Cocinero("Maria Castañera", "33333333", "Mujer", 23, 0, "Entrantes");
        Cocinero c3 = new Cocinero("Anonimo", "888444568", "Anonimo", 40, 10, "Platos principales");
        Plato p1 = new Plato("Tarta tres chocolates", "Postre", 12.50, c1);
        Plato p2 = new Plato("Ensalada de la huerta", "Entrantes", 9.90, c3);

        // TEST:        
        System.out.println("************************************************************");
        System.out.println("Testeando conexión con la base de datos...");
        try {
            restaurantDAO.conectar();
            System.out.println("Establecida la conexión.");
            System.out.println("************************************************************");
            System.out.println("Testeando insert cocinero " + c1.getNombre());
            altaCocinero(restaurantDAO, c1);
            System.out.println("************************************************************");
            System.out.println("Testeando insert cocinero duplicado " + c2.getNombre());
            altaCocinero(restaurantDAO, c2);
            System.out.println("************************************************************");
            System.out.println("Listado de todos los cocineros");
            List<Cocinero> cos = restaurantDAO.selectAllCocinero();
            for (Cocinero c : cos) {
                System.out.println(c);
            }
            System.out.println("************************************************************");
            System.out.println("Testeando insert plato: " + p1.getNombre());
            altaPlato(restaurantDAO, p1);
            System.out.println("************************************************************");
            System.out.println("Testeando insert plato duplicado " + p1.getNombre());
            altaPlato(restaurantDAO, p1);
            System.out.println("************************************************************");
            System.out.println("************************************************************");
            System.out.println("Testeando insert plato con cocinero que no existe " + p2.getNombre());
            altaPlato(restaurantDAO, p2);
            System.out.println("************************************************************");
            System.out.println("************************************************************");
            System.out.println("Testeando obtener plato a partir del nombre: Lentejas");
            try {
                Plato aux = restaurantDAO.getPlatoByNombre("Garbanzos");
                System.out.println("Datos del plato");
                System.out.println(aux);
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Cerrando conexión con la base de datos");
            restaurantDAO.desconectar();
            System.out.println("Conexión cerrada.");
        } catch (SQLException ex) {
            System.out.println("Error al conectar / desconectar: " + ex.getMessage());
        }

    }

    private static void altaPlato(RestaurantDAO restaurantDAO, Plato p1) throws SQLException {
        try {
            restaurantDAO.insertarPlato(p1);
            System.out.println("Plato dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void altaCocinero(RestaurantDAO restaurantDAO, Cocinero c1) throws SQLException {
        try {
            restaurantDAO.insertarCocinero(c1);
            System.out.println("Cocinero dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

}

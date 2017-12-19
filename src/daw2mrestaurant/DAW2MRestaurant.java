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
import modelo.RankingCocineroTO;

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
        Cocinero c1 = new Cocinero("Maria Lapera", "666554433", "Mujer", 33, 6, "Postres");
        Cocinero c2 = new Cocinero("Karlos Arguiñano", "33333333", "Mujer", 23, 0, "Entrantes");
        Cocinero c3 = new Cocinero("Anonimo", "888444568", "Anonimo", 40, 10, "Platos principales");
        Cocinero c4 = new Cocinero("Cocinero Auxiliar", "999999999", "Auxiliar", 10, 10, "Auxiliar");
        Plato p1 = new Plato("Macedonia", "Postre", 4.20, c1);
        Plato p2 = new Plato("Espaguetis carbonara", "Platos principales", 6.20, c2);
        Plato p3 = new Plato("Ensalada de la huerta", "Entrantes", 9.90, c3);

        // TEST:        
        System.out.println("************************************************************");
        System.out.println("Testeando conexión con la base de datos...");
        try {
            restaurantDAO.conectar();
            System.out.println("Establecida la conexión.");
            System.out.println("************************************************************");
            System.out.println("Testeando insert cocinero " + c4.getNombre());
            altaCocinero(restaurantDAO, c4);
            System.out.println("************************************************************");
            System.out.println("Testeando insert cocinero duplicado " + c4.getNombre());
            altaCocinero(restaurantDAO, c4);
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
            System.out.println("Testeando insert plato con cocinero que no existe " + p3.getNombre());
            altaPlato(restaurantDAO, p3);
            System.out.println("************************************************************");
            System.out.println("Testeando obtener plato a partir del nombre: Lentejas");
            obtenerPlato(restaurantDAO, "Lentejas");
            System.out.println("************************************************************");
            System.out.println("Testeando obtener plato a partir del nombre (cuando no existe): Garbanzos");
            obtenerPlato(restaurantDAO, "Garbanzos");
            System.out.println("************************************************************");
            System.out.println("Testeando ranking de cocineros");
            List<RankingCocineroTO> ranking = restaurantDAO.rankingCocineros();
            for (RankingCocineroTO r : ranking) {
                System.out.println(r);
            }
            System.out.println("************************************************************");
            System.out.println("Testeando borrar cocinero " + c1.getNombre());
            borrarCocinero(restaurantDAO, c4);
            System.out.println("************************************************************");
            System.out.println("Testeando borrar cocinero que ya no existe " + c1.getNombre());
            borrarCocinero(restaurantDAO, c4);
            System.out.println("************************************************************");
            System.out.println("Testeando borrar plato " + p1.getNombre());
            borrarPlato(restaurantDAO, p1);
            System.out.println("************************************************************");
            System.out.println("Testeando borrar plato que ya no existe  " + p1.getNombre());
            borrarPlato(restaurantDAO, p1);
            System.out.println("************************************************************");
            System.out.println("Cerrando conexión con la base de datos");
            restaurantDAO.desconectar();
            System.out.println("Conexión cerrada.");
            System.out.println("************************************************************");
        } catch (SQLException ex) {
            System.out.println("Error al conectar / desconectar: " + ex.getMessage());
        }

    }

    private static void borrarPlato(RestaurantDAO restaurantDAO, Plato p1) throws SQLException {
        try {
            restaurantDAO.borrarPlato(p1);
            System.out.println("Plato borrado de la base de datos.");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void borrarCocinero(RestaurantDAO restaurantDAO, Cocinero c1) throws SQLException {
        try {
            restaurantDAO.borrarCocinero(c1);
            System.out.println("Cocinero borrado de la base de datos");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void obtenerPlato(RestaurantDAO restaurantDAO, String nombrePlato) throws SQLException {
        try {
            Plato aux = restaurantDAO.getPlatoByNombre(nombrePlato);
            System.out.println("Datos del plato");
            System.out.println(aux);
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
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

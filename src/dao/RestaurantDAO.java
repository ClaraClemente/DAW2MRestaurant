/*
 * Clase que se encarga de la persistencia en la BBDD
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Cocinero;

/**
 *
 * @author mfontana
 */
public class RestaurantDAO {
    private Connection conexion;
    
    public void insertarCocinero(Cocinero c) throws SQLException {
        // Definimos la consulta
        String insert = "insert into cocinero values (?, ?, ?, ?, ?, ?)";
        // Necesitamos preparar la consulta parametrizada
        PreparedStatement ps = conexion.prepareStatement(insert);
        // Le damos valor a los interrogantes
        ps.setString(1, c.getNombre());
        ps.setString(2, c.getTelefono());
        ps.setString(3, c.getSexo());
        ps.setInt(4, c.getEdad());
        ps.setInt(5, c.getExperiencia());
        ps.setString(6, c.getEspecialidad());
        // Ejecutamos la consulta
        ps.executeUpdate();
        // cerramos recursos
        ps.close();
    }
    
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String user = "root";
        String pass = "root";
        conexion = DriverManager.getConnection(url, user, pass);
    }
    
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}

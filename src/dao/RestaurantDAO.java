/*
 * Clase que se encarga de la persistencia en la BBDD
 */
package dao;

import excepciones.MiExcepcion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cocinero;

/**
 *
 * @author mfontana
 */
public class RestaurantDAO {

    private Connection conexion;

    // Función que devuelve los datos de todos los cocineros
    public List<Cocinero> selectAllCocinero() throws SQLException {
        List<Cocinero> cocineros = new ArrayList<>();
        String select = "select * from cocinero";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        // Recorremos el resultado
        while (rs.next()) {
            Cocinero c = new Cocinero();
            c.setNombre(rs.getString("nombre"));
            c.setEdad(rs.getInt("edad"));
            c.setEspecialidad(rs.getString("especialidad"));
            c.setExperiencia(rs.getInt("experiencia"));
            c.setTelefono(rs.getString("telefono"));
            c.setSexo(rs.getString("sexo"));
            cocineros.add(c);
        }
        // Cerrar recursos
        rs.close();
        st.close();
        return cocineros;
    }

    public void insertarCocinero(Cocinero c) throws SQLException, MiExcepcion {
        if (existeCocinero(c)) {
            throw new MiExcepcion("Ya existe un cocinero con ese nombre");
        } else {
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
    }

    private boolean existeCocinero(Cocinero c) throws SQLException {
        String select = "select * from cocinero where nombre='" + c.getNombre() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
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

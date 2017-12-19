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
import modelo.Plato;

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

    // Función que devuelve un plato a partir del nombre
    public Plato getPlatoByNombre(String nombre) throws SQLException, MiExcepcion {
        // Plato auxiliar para comprobar si ya existe
        Plato aux = new Plato(nombre);
        if (!existePlato(aux)) {
            throw new MiExcepcion("ERROR: No existe ningún plato con ese nombre");
        }
        String select = "select * from plato where nombre='" + nombre + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        Plato p = new Plato();
        // Como sólo habrá un resultado no hace falta while
        if (rs.next()) {
            p.setNombre(rs.getString("nombre"));
            p.setPrecio(rs.getDouble("precio"));
            p.setTipo(rs.getString("tipo"));
            p.setCocinero(selectCocineroByNombre(rs.getString("cocinero")));
        }
        rs.close();
        st.close();
        return p;
    }

    // Función que devuelve un cocinero a partir del nombre
    public Cocinero selectCocineroByNombre(String nombre) throws MiExcepcion, SQLException {
        Cocinero aux = new Cocinero(nombre);
        if (!existeCocinero(aux)) {
            throw new MiExcepcion("ERROR: No existe ningún cocinero con ese nombre");
        }
        String select = "select * from cocinero where nombre='" + nombre + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        Cocinero c = new Cocinero();
        if (rs.next()) {
            c.setNombre(rs.getString("nombre"));
            c.setEdad(rs.getInt("edad"));
            c.setEspecialidad(rs.getString("especialidad"));
            c.setExperiencia(rs.getInt("experiencia"));
            c.setSexo(rs.getString("sexo"));
            c.setTelefono(rs.getString("telefono"));
        }
        rs.close();
        st.close();
        return c;
    }

    // Función que da de alta un plato 
    public void insertarPlato(Plato p) throws SQLException, MiExcepcion {
        // Tenemos que asegurarnos de que no existe un plato con el mismo 
        // nombre para evitar error de primary key
        if (existePlato(p)) {
            throw new MiExcepcion("ERROR: Ya existe un plato con ese nombre");
        } else // en realidad este else no hace falta
        // Tenemos que asegurarnos de que existe el cocinero, sino
        // daría error de foreign key
        {
            if (!existeCocinero(p.getCocinero())) {
                throw new MiExcepcion("ERROR: No existe el cocinero. No se puede dar de alta el plato.");
            } else {
                String insert = "insert into plato values (?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(insert);
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getTipo());
                ps.setDouble(3, p.getPrecio());
                ps.setString(4, p.getCocinero().getNombre());
                ps.executeUpdate();
                ps.close();
            }
        }
    }

    public void insertarCocinero(Cocinero c) throws SQLException, MiExcepcion {
        if (existeCocinero(c)) {
            throw new MiExcepcion("ERROR: Ya existe un cocinero con ese nombre");
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

    private boolean existePlato(Plato p) throws SQLException {
        String select = "select * from plato where nombre='" + p.getNombre() + "'";
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

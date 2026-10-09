import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/modulo_jdbc";
        String usuario = "root";
        String password = "root";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("¡Conexión exitosa!\n");

            // Crear el Statement
            Statement statement = conexion.createStatement();

            // Ejecutar la consulta
            String sql = "SELECT * FROM usuarios";
            ResultSet resultado = statement.executeQuery(sql);

            // Recorrer los resultados
            System.out.println("ID | Nombre          | Correo              | Edad");
            System.out.println("------------------------------------------------");

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String correo = resultado.getString("correo");
                int edad = resultado.getInt("edad");

                System.out.println(id + "  | " + nombre + "     | " + correo + "   | " + edad);
            }

            // Cerrar recursos
            resultado.close();
            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

package pasos;
import java.sql.Connection;
import java.sql.DriverManager;
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

            Statement statement = conexion.createStatement();

            // Insertar un nuevo usuario
            String sql = "INSERT INTO usuarios (nombre, correo, edad) VALUES ('Carlos Ramirez', 'carlos@email.com', 25)";
            
            int filasAfectadas = statement.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el usuario.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

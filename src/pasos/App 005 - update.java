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

            // Actualizar el correo del usuario con id = 1
            String sql = "UPDATE usuarios SET correo = 'juan.perez@nuevoemail.com', edad = 29 WHERE id = 1";
            
            int filasAfectadas = statement.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró el usuario para actualizar.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

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

            // Eliminar el usuario con id = 3 (el que insertamos anteriormente)
            String sql = "DELETE FROM usuarios WHERE id = 3";
            
            int filasAfectadas = statement.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró el usuario para eliminar.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

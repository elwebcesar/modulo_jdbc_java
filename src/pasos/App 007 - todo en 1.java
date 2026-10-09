import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/modulo_jdbc";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    insertarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    actualizarUsuario(scanner);
                    break;
                case 4:
                    eliminarUsuario(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            System.out.println();

        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("========== MENÚ CRUD USUARIOS ==========");
        System.out.println("1. Insertar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Actualizar usuario");
        System.out.println("4. Eliminar usuario");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    // ========== INSERTAR ==========
    private static void insertarUsuario(Scanner scanner) {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Correo: ");
            String correo = scanner.nextLine();

            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();

            Connection conexion = obtenerConexion();
            Statement statement = conexion.createStatement();

            String sql = "INSERT INTO usuarios (nombre, correo, edad) VALUES ('" 
                         + nombre + "', '" + correo + "', " + edad + ")";

            int filas = statement.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Usuario insertado correctamente.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // ========== LISTAR (CONSULTAR) ==========
    private static void listarUsuarios() {
        try {
            Connection conexion = obtenerConexion();
            Statement statement = conexion.createStatement();

            ResultSet resultado = statement.executeQuery("SELECT * FROM usuarios");

            System.out.println("\nID | Nombre              | Correo                     | Edad");
            System.out.println("---------------------------------------------------------------");

            while (resultado.next()) {
                System.out.printf("%-3d| %-20s| %-27s| %d%n",
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("correo"),
                        resultado.getInt("edad"));
            }

            resultado.close();
            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    // ========== ACTUALIZAR ==========
    private static void actualizarUsuario(Scanner scanner) {
        try {
            System.out.print("ID del usuario a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Nuevo correo: ");
            String correo = scanner.nextLine();

            System.out.print("Nueva edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();

            Connection conexion = obtenerConexion();
            Statement statement = conexion.createStatement();

            String sql = "UPDATE usuarios SET nombre = '" + nombre 
                         + "', correo = '" + correo 
                         + "', edad = " + edad 
                         + " WHERE id = " + id;

            int filas = statement.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró un usuario con ese ID.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    // ========== ELIMINAR ==========
    private static void eliminarUsuario(Scanner scanner) {
        try {
            System.out.print("ID del usuario a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Connection conexion = obtenerConexion();
            Statement statement = conexion.createStatement();

            String sql = "DELETE FROM usuarios WHERE id = " + id;

            int filas = statement.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró un usuario con ese ID.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}

import dao.UsuarioDAO;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    usuarioDAO.insertar(nombre, correo, edad);
                    break;

                case 2:
                    usuarioDAO.listar();
                    break;

                case 3:
                    System.out.print("ID del usuario a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nuevo correo: ");
                    String nuevoCorreo = scanner.nextLine();
                    System.out.print("Nueva edad: ");
                    int nuevaEdad = scanner.nextInt();
                    scanner.nextLine();
                    usuarioDAO.actualizar(idActualizar, nuevoNombre, nuevoCorreo, nuevaEdad);
                    break;

                case 4:
                    System.out.print("ID del usuario a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    usuarioDAO.eliminar(idEliminar);
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
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
}

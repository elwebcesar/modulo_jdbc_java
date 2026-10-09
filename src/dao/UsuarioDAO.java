package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    public void insertar(String nombre, String correo, int edad) {
        String sql = "INSERT INTO usuarios (nombre, correo, edad) VALUES ('" 
                     + nombre + "', '" + correo + "', " + edad + ")";
        ejecutarUpdate(sql, "Usuario insertado correctamente.");
    }

    public void listar() {
        try {
            Connection conexion = ConexionBD.obtenerConexion();
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

    public void actualizar(int id, String nombre, String correo, int edad) {
        String sql = "UPDATE usuarios SET nombre = '" + nombre 
                     + "', correo = '" + correo 
                     + "', edad = " + edad 
                     + " WHERE id = " + id;
        ejecutarUpdate(sql, "Usuario actualizado correctamente.");
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = " + id;
        ejecutarUpdate(sql, "Usuario eliminado correctamente.");
    }

    private void ejecutarUpdate(String sql, String mensajeExito) {
        try {
            Connection conexion = ConexionBD.obtenerConexion();
            Statement statement = conexion.createStatement();
            int filas = statement.executeUpdate(sql);

            if (filas > 0) {
                System.out.println(mensajeExito);
            } else {
                System.out.println("No se encontró el registro.");
            }

            statement.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

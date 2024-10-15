package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseModel {
    private final String DB_URL = "jdbc:mysql://localhost:3306/peluqueria"; // Cambia la URL si es necesario
    private final String USER = "tu_usuario"; // Cambia a tu usuario de la base de datos
    private final String PASSWORD = "tu_contraseña"; // Cambia a tu contraseña de la base de datos

    public boolean validarCredenciales(String usuario, String contrasena) {
        String query = "SELECT * FROM trabajadores WHERE email = ? AND contrasena = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);

            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Si hay resultados, las credenciales son válidas

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Si algo falla o no hay resultados, devuelve false
    }
}

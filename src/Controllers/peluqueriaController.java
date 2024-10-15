package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class peluqueriaController {
    @FXML
    private Label labMensaje;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pwdCampo;

    private final String DB_URL = "jdbc:mysql://localhost:3306/peluqueria";
    private final String USER = "root";
    private final String PASSWORD = "";

    @FXML
    public void bthpresionado(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String contrasena = pwdCampo.getText();

        if (validarCredenciales(usuario, contrasena)) {
            mostrarPaginaBienvenida(event);
        } else {
            labMensaje.setText("Usuario o contraseña incorrectos.");
        }
    }

    private boolean validarCredenciales(String usuario, String contrasena) {
        String query = "SELECT * FROM trabajadores WHERE email = ? AND contrasena = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, usuario);
                pstmt.setString(2, contrasena);

                ResultSet rs = pstmt.executeQuery();

                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            labMensaje.setText("Error de conexión: " + e.getMessage());
        }

        return false;
    }

    private void mostrarPaginaBienvenida(ActionEvent event) {
        try {
            // Carga el archivo FXML de la página de bienvenida
        	Parent bienvenidoRoot = FXMLLoader.load(getClass().getResource("/Views/bienvenido.fxml"));
            
            // Obtén la ventana actual desde el evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Crea una nueva escena con la vista de bienvenida
            Scene scene = new Scene(bienvenidoRoot);
            
            // Cambia la escena de la ventana actual
            stage.setScene(scene);
            stage.setTitle("Bienvenido");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

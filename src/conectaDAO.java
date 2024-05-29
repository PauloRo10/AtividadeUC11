
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;

        try {
            // Certifique-se de que o driver JDBC está carregado
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão com o banco de dados
            String url = "jdbc:mysql://localhost/uc11?useSSL=false";
            conn = DriverManager.getConnection(url, "root", "mariana10");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão: " + e.getMessage());
        }
        
        return conn;
    }
    
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
    
}

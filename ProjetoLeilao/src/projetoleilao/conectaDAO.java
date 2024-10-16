/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoleilao;

/**
 *
 * @author Beto
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    
    public Connection connectDB() {
        Connection conn = null;

        try {
            // Conectar ao banco de dados MySQL com URL correta
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "Samuel2002"); 
            // Substitua "root" e "" pela senha correta, caso haja uma.
        } catch (SQLException erro) {
            // Exibe a mensagem de erro em caso de falha na conexão
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }

        return conn;
    }
}


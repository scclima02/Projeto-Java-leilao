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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    // Método para cadastrar produto no banco de dados
    public boolean cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();  // Conectar ao banco de dados

        try {
            // Prepara a instrução SQL para inserir os dados no banco de dados
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());   // Definir o nome
            prep.setInt(2, produto.getValor());     // Definir o valor (supondo que seja um inteiro)
            prep.setString(3, produto.getStatus()); // Definir o status ("A Venda")

            // Executa a atualização no banco de dados
            prep.executeUpdate();
            prep.close();

            return true; // Cadastro bem-sucedido
        } catch (SQLException e) {
            // Exibe a mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
            return false; // Falha no cadastro
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Fechar a conexão com o banco de dados
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

   
    
    // Método para listar os produtos cadastrados no banco de dados
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos"; // Supondo que a tabela se chama 'produtos'
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        
        return listagem;
    }

    // Método para vender um produto (altera o status do produto para 'Vendido')
    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}

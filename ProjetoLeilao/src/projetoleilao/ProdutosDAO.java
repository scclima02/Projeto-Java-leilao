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

    static int getSelectedRow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    // SQL para buscar todos os produtos com status 'Vendido'
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    
    // Inicia a conexão com o banco de dados
    conn = new conectaDAO().connectDB();
    
    ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();
    
    try {
        // Prepara e executa a consulta SQL
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        // Loop para obter os dados dos produtos vendidos
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            // Adiciona o produto na lista de produtos vendidos
            listagemVendidos.add(produto);
        }
        
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
    } finally {
        try {
            // Fecha a conexão
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
    // Retorna a lista de produtos vendidos
    return listagemVendidos;
}


    // Método para vender um produto (altera o status do produto para 'Vendido')
    public void venderProduto(int id) {
    // SQL para alterar o status do produto para "Vendido" com base no ID
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    
    // Inicia a conexão com o banco de dados
    conn = new conectaDAO().connectDB();
    
    try {
        // Prepara a instrução SQL
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id); // Define o valor do parâmetro ID

        // Executa a atualização
        int rowsAffected = prep.executeUpdate();
        
        // Verifica se algum registro foi atualizado
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto com ID " + id + " não encontrado.");
        }
        
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage());
    } finally {
        try {
            // Fecha a conexão
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
}

    ArrayList<ProdutosDTO> listarProdutos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

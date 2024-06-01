/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    conectaDAO cd =new conectaDAO();
    public void cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
       try { 
        conn = new conectaDAO().connectDB();
        
        String sql = "Insert into produtos( nome , valor, status) values(?,?,?)";
        
        PreparedStatement query = conn.prepareStatement(sql);
        
        query.setString(1, produto.getNome());
        query.setInt(2,produto.getValor());
        query.setString(3,produto.getStatus());
        
        query.execute();
        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso");
         
       }
       catch (SQLException se) {
            System.out.println("Erro ao cadastrar registro no banco de dados");
            JOptionPane.showMessageDialog(null,"Cadastro não realizado");
            
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();
        conn = new conectaDAO().connectDB();
        
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer a conexão com o banco de dados.");
            return listaProdutos;
        }

        String sql = "SELECT id, nome, valor, status FROM produtos";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("Id"));
                produto.setNome(rs.getString("Nome"));
                produto.setValor(rs.getInt("Valor"));
                produto.setStatus(rs.getString("Status"));
                listaProdutos.add(produto);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            cd.closeConnection(conn);
        }
        
        return listaProdutos;
    }
    
    public  void venderProduto(ProdutosDTO p){
        try { 
       
        
       conn = new conectaDAO().connectDB();
        
String sql = "UPDATE produtos SET status=? WHERE id=?;";
            PreparedStatement consulta = conn.prepareStatement(sql);
            
            //consulta = conn.prepareStatement(sql);
            
             consulta.setString(1, p.getStatus());
             consulta.setInt(2, p.getId());

             
             consulta.executeUpdate();
             
             cd.closeConnection(conn);
            
       }
       catch (SQLException se) {
            System.out.println("Erro ao cadastrar registro no banco de dados");
            JOptionPane.showMessageDialog(null,"Cadastro não realizado");
            
        }
    }
    
     public List<ProdutosDTO> listarProdutosVendidos() {
        List<ProdutosDTO> produtosVendidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = ?";
            consulta = conn.prepareStatement(sql);
            consulta.setString(1, "Vendido");
            
            resultado = consulta.executeQuery();
            
            while (resultado.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(resultado.getInt("valor"));
                produto.setStatus(resultado.getString("status"));
                
                produtosVendidos.add(produto);
            }
        } catch (SQLException se) {
            System.out.println("Erro ao listar produtos vendidos: " + se.getMessage());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (consulta != null) consulta.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("Erro ao fechar recursos: " + se.getMessage());
            }
        }

        return produtosVendidos;
    }
        
    }
    
    
    
        



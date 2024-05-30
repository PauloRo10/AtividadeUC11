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


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
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
        
        return listagem;
    }
    
    
    
        
}


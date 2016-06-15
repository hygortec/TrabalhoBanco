package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {   
    
    public static Connection getConexao(String servidor, String banco, String usuario, String senha) throws SQLException{
         
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conm = DriverManager.getConnection("jdbc:mysql://"+servidor,usuario,senha);
            String sql = "";

            PreparedStatement stmt = conm.prepareStatement(sql);
            stmt.close();
            conm.close();
            
            return DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+banco,usuario,senha);
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao banco de dados");
            throw new SQLDataException(ex.getMessage());
        }
        
        
        
        
    }
    
}

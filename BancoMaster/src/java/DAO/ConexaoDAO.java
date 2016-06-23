package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {   
    
    public static Connection getConexao() throws SQLException{
        //Parametro de conexão banco remoto
        // Servidor: db4free.net
        // banco: bancomaster
        // Usuario Banco: bancomaster  
        // Senha: shaman
         
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conm = DriverManager.getConnection("jdbc:mysql://db4free.net","bancomaster","shaman");
            //Connection conm = DriverManager.getConnection("jdbc:mysql://localhost","root","cronos");
            String sql = "use bancomaster";

            PreparedStatement stmt = conm.prepareStatement(sql);
            stmt.close();
            conm.close();
            
            return DriverManager.getConnection("jdbc:mysql://db4free.net/bancomaster","bancomaster","shaman");
            //return DriverManager.getConnection("jdbc:mysql://localhost/bancomaster","root","cronos");
        }catch(ClassNotFoundException ex){
            throw new SQLDataException(ex.getMessage());
        }
    }
    
}

package DAO;

import BEAN.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author higor
 */
public class PessoaDAO {
    
    public void cadastrar(Pessoa objPessoa) throws SQLException{
        Connection con = ConexaoDAO.getConexao("db4free.net", "bancomaster", "bancomaster", "shaman");
        String sql = "INSERT INTO T001_Pessoa(T001_Nome, T001_Endereco, T001_cpf, T001_rg, T001_Telefone, T001_Sexo, T001_TipoPessoa, T001_RendaMensal) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        stmt.setString(1, objPessoa.getNome());
        stmt.setString(2, objPessoa.getEndereco());
        stmt.setString(3, objPessoa.getCpf());
        stmt.setString(4, objPessoa.getRg());
        stmt.setString(5, objPessoa.getTelefone());
        stmt.setInt(6, objPessoa.getSexo());
        stmt.setInt(7, objPessoa.getTipoPessoa());
        stmt.setFloat(8, objPessoa.getRendaMensal());  

        stmt.execute();
        stmt.close();
    }
    
}

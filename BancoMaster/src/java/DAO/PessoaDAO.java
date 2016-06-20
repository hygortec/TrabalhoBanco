package DAO;

import BEAN.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author higor
 */
public class PessoaDAO {
    /** Método para cadatrar pessoa, com todos os dados da tabela do banco
     *   @return void 
     *   @throws Retorno do banco de dados*/
    public void cadastrar(Pessoa objPessoa) throws SQLException{
        Connection con = ConexaoDAO.getConexao();
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
    
    /** Método para alterar uma pessoa no banco
     *   @param objPessoa Pessoa - Objeto do tipo pessoa.
     *   @param id int - ID da tabela pessoa a qual deve ser alterado.
     *   @return void 
     *   @throws Retorno do banco de dados*/
    public void alterar(Pessoa objPessoa, int id) throws SQLException{        
        Connection con = ConexaoDAO.getConexao();
        String sql = "update T001_Pessoa set T001_Nome=?, T001_Endereco=?, T001_cpf=?, T001_rg=?, T001_Telefone=?, T001_Sexo=?, T001_TipoPessoa=?, T001_RendaMensal=? where T001_id = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        stmt.setString(1, objPessoa.getNome());
        stmt.setString(2, objPessoa.getEndereco());
        stmt.setString(3, objPessoa.getCpf());
        stmt.setString(4, objPessoa.getRg());
        stmt.setString(5, objPessoa.getTelefone());
        stmt.setInt(6, objPessoa.getSexo());
        stmt.setInt(7, objPessoa.getTipoPessoa());
        stmt.setFloat(8, objPessoa.getRendaMensal());  
        stmt.setFloat(9, objPessoa.getId()); 

        stmt.execute();
        stmt.close();       
        
    }
    
    /** Método para excluir uma pessoa no banco
     *   @param id int - ID da tabela pessoa a qual deve ser excluido.
     *   @return void 
     *   @throws Retorno do banco de dados*/
    public void remover(int id) throws SQLException{
        Connection con = ConexaoDAO.getConexao();
        String sql = "DELETE FROM T001_Pessoa WHERE T001_id = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        con.close();
    }    
    
    /** Método para consultar uma pessoa no banco pelo cpf
     *   @param cpf String - cpf da pessoa a qual deve ser consultado.
     *   @return Pessoa 
     *   @throws Retorno do banco de dados*/
    public Pessoa consultar(String cpf) throws SQLException{
        Pessoa objPessoa = new Pessoa();
        
        Connection con = ConexaoDAO.getConexao();
        String sql = "SELECT * FROM T001_Pessoa WHERE T001_cpf like '%" + cpf + "%'";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        resultado.next();
        //T001_Telefone, T001_Sexo, T001_TipoPessoa, T001_RendaMensal
        objPessoa.setId(resultado.getInt("T001_id"));
        objPessoa.setNome(resultado.getString("T001_nome"));
        objPessoa.setEndereco(resultado.getString("T001_Endereco"));
        objPessoa.setCpf(resultado.getString("T001_cpf"));
        objPessoa.setRg(resultado.getString("T001_rg"));
        objPessoa.setTelefone(resultado.getString("T001_Telefone"));
        objPessoa.setSexo(resultado.getInt("T001_Sexo"));
        objPessoa.setTipoPessoa(resultado.getInt("T001_TipoPessoa"));
        objPessoa.setRendaMensal(resultado.getFloat("T001_RendaMensal"));
        
        stmt.close();
        con.close();
        
        return objPessoa;
    }
    
    /** Método para consultar uma pessoa no banco pelo cpf
     *   @param cpf String - cpf da pessoa a qual deve ser consultado.
     *   @return Pessoa 
     *   @throws Retorno do banco de dados*/
    public Pessoa consultarId(int id) throws SQLException{
        Pessoa objPessoa = new Pessoa();
        
        Connection con = ConexaoDAO.getConexao();
        String sql = "SELECT * FROM T001_Pessoa WHERE T001_id like '%" + id + "%'";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        resultado.next();
        //T001_Telefone, T001_Sexo, T001_TipoPessoa, T001_RendaMensal
        objPessoa.setId(resultado.getInt("T001_id"));
        objPessoa.setNome(resultado.getString("T001_nome"));
        objPessoa.setEndereco(resultado.getString("T001_Endereco"));
        objPessoa.setCpf(resultado.getString("T001_cpf"));
        objPessoa.setRg(resultado.getString("T001_rg"));
        objPessoa.setTelefone(resultado.getString("T001_Telefone"));
        objPessoa.setSexo(resultado.getInt("T001_Sexo"));
        objPessoa.setTipoPessoa(resultado.getInt("T001_TipoPessoa"));
        objPessoa.setRendaMensal(resultado.getFloat("T001_RendaMensal"));
        
        stmt.close();
        con.close();
        
        return objPessoa;
    }
}

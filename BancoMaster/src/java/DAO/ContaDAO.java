package DAO;

import BEAN.Conta;
import BEAN.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author higor
 */
public class ContaDAO {
    
    
    public Conta gerarNovaConta(Pessoa objPessoa, String senha){
        Conta objConta = new Conta();
        Random geradorAleatorio = new Random(); 
        int agencia = geradorAleatorio.nextInt(9999999);
        agencia += 40000000; 
        
        int numero = geradorAleatorio.nextInt(999999);
        String aux = numero+objPessoa.getCpf().substring(9, 11);
        numero = Integer.parseInt(aux); 
        
        objConta.setAgencia(agencia);
        objConta.setNumero(numero);
        objConta.setId_pessoa(objPessoa.getId());
        objConta.setLimite(0.00f);
        objConta.setSaldo(0.00f);
        objConta.setSenha(senha);
        return objConta;
    }
    
    public void cadastrar(Conta objConta) throws SQLException{
        Connection con = ConexaoDAO.getConexao();
        String sql = "INSERT INTO T002_Conta(T002_Numero, T002_Agencia, T002_Limite, T002_Saldo, T001_id, T002_Senha) VALUES (?,?,?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        stmt.setInt(1, objConta.getNumero());
        stmt.setInt(2, objConta.getAgencia());
        stmt.setFloat(3, objConta.getLimite());
        stmt.setFloat(4, objConta.getSaldo());
        stmt.setInt(5, objConta.getId_pessoa());  
        stmt.setString(6, objConta.getSenha());

        stmt.execute();
        stmt.close();
    }
    
    /** Método para alterar uma pessoa no banco
     *   @param objPessoa Pessoa - Objeto do tipo pessoa.
     *   @param id int - ID da tabela pessoa a qual deve ser alterado.
     *   @return void 
     *   @throws Retorno do banco de dados*/
    public Conta consultar(int numero) throws SQLException{ 
        Conta objConta = new Conta();
        Connection con = ConexaoDAO.getConexao();
        String sql = "SELECT * FROM T002_Conta WHERE T002_Numero like '%" + numero + "%'";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        resultado.next();
        
        objConta.setId(resultado.getInt("T002_id"));
        objConta.setNumero(resultado.getInt("T002_Numero"));
        objConta.setAgencia(resultado.getInt("T002_Agencia"));
        objConta.setLimite(resultado.getFloat("T002_Limite"));
        objConta.setSaldo(resultado.getFloat("T002_Saldo"));
        objConta.setSenha(resultado.getString("T002_Senha"));
        objConta.setId(resultado.getInt("T001_id"));
        
        stmt.close();
        con.close();
        
        return objConta;
    }
    
    /** Método para alterar uma pessoa no banco
     *   @param objPessoa Pessoa - Objeto do tipo pessoa.
     *   @param id int - ID da tabela pessoa a qual deve ser alterado.
     *   @return void 
     *   @throws Retorno do banco de dados*/
    public void alterar(Conta objPessoa, int id) throws SQLException{        
        Connection con = ConexaoDAO.getConexao();
        String sql = "update T001_Pessoa set T001_Nome=?, T001_Endereco=?, T001_cpf=?, T001_rg=?, T001_Telefone=?, T001_Sexo=?, T001_TipoPessoa=?, T001_RendaMensal=? where T001_id = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        

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
    
}

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
        
        //Regra de gerar a agenci sempre o 4 no inicio;
        Random geradorAleatorio = new Random(); 
        int agencia = geradorAleatorio.nextInt(9999999);
        agencia += 40000000; 
        //Regra de gerar a conta sempre o dois ultimo sdigitos do cpf no final;
        int numero = geradorAleatorio.nextInt(999999);
        String aux = numero+objPessoa.getCpf().substring(9, 11);
        numero = Integer.parseInt(aux); 
        
        objConta.setAgencia(agencia);
        objConta.setNumero(numero);
        objConta.setId_pessoa(objPessoa.getId());
        objConta.setLimiteAtual((objPessoa.getRendaMensal()*0.7f));//Regra de gerar o limite com base em 70% da renda;
        objConta.setLimite(objConta.getLimiteAtual());
        objConta.setSaldo(0.00f);
        objConta.setSenha(senha);
        objConta.setEmprestimos(0.00f);
        objConta.setPrazoEmprestimos(0); 
        objConta.setFinanciamento(0.00f);
        objConta.setPrazoFinanciamento(0);
        return objConta;
    }
    
    public void cadastrar(Conta objConta) throws SQLException{
        Connection con = ConexaoDAO.getConexao();
        String sql = "INSERT INTO T002_Conta(T002_Numero, T002_Agencia, T002_LimiteAtual, T002_Saldo, T001_id, T002_Senha, T002_Limite, T002_Emprestimo, T002_PrazoEmprestimo, T002_Financiamento, T002_PrazoFinanciamento) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        stmt.setInt(1, objConta.getNumero());
        stmt.setInt(2, objConta.getAgencia());
        stmt.setFloat(3, objConta.getLimiteAtual());
        stmt.setFloat(4, objConta.getSaldo());
        stmt.setInt(5, objConta.getId_pessoa());  
        stmt.setString(6, objConta.getSenha());
        stmt.setFloat(7, objConta.getLimite());
        stmt.setFloat(8, objConta.getEmprestimos());
        stmt.setInt(9, objConta.getPrazoEmprestimos());
        stmt.setFloat(10, objConta.getFinanciamento());
        stmt.setInt(11, objConta.getPrazoFinanciamento());

        stmt.execute();
        stmt.close();
    }
    
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
        objConta.setLimiteAtual(resultado.getFloat("T002_LimiteAtual"));
        objConta.setLimite(resultado.getFloat("T002_Limite"));
        objConta.setSaldo(resultado.getFloat("T002_Saldo"));
        objConta.setSenha(resultado.getString("T002_Senha"));
        objConta.setId_pessoa(resultado.getInt("T001_id"));
        objConta.setEmprestimos(resultado.getFloat("T002_Emprestimo"));
        objConta.setPrazoEmprestimos(resultado.getInt("T002_PrazoEmprestimo")); 
        objConta.setFinanciamento(resultado.getFloat("T002_Financiamento"));
        objConta.setPrazoFinanciamento(resultado.getInt("T002_PrazoFinanciamento"));
        
        stmt.close();
        con.close();
        
        return objConta;
    }
    
   
    public void alterar(Conta objConta) throws SQLException{        
        Connection con = ConexaoDAO.getConexao();
        String sql = "UPDATE t002_conta  SET  T002_Numero =?,  T002_Agencia =?,  T002_LimiteAtual =?,  T002_Saldo =?,  T002_Senha =?, T002_Limite=?, T002_Emprestimo=?, T002_PrazoEmprestimo=?, T002_Financiamento=?, T002_PrazoFinanciamento=? WHERE  T002_id =?  and T001_id =?";
        //UPDATE t002_conta  SET  T002_Numero =?,  T002_Agencia =?,  T002_Limite =?,  T002_Saldo =?,  T002_Senha =?  WHERE  T002_id =?  and T001_id =? ;

        
        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        stmt.setInt(1, objConta.getNumero());
        stmt.setInt(2, objConta.getAgencia());
        stmt.setFloat(3, objConta.getLimiteAtual());
        stmt.setFloat(4, objConta.getSaldo());
        stmt.setString(5, objConta.getSenha());
        stmt.setFloat(6, objConta.getLimite());
        stmt.setFloat(7, objConta.getEmprestimos());
        stmt.setInt(8, objConta.getPrazoEmprestimos());
        stmt.setFloat(9, objConta.getFinanciamento());
        stmt.setInt(10, objConta.getPrazoFinanciamento());
        stmt.setInt(11, objConta.getId_pessoa());  
        stmt.setInt(12, objConta.getId());

        stmt.execute();
        stmt.close();      
        
    }
    
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

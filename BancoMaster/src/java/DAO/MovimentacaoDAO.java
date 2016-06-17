package DAO;

import BEAN.Conta;
import BEAN.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author higor
 */
public class MovimentacaoDAO {
    
    public void movimentar(Movimentacao objMovimentacao) throws SQLException{
        Connection con = ConexaoDAO.getConexao();
        String sql = "INSERT INTO T003_MovimentacaoConta(T003_DataLancamento, T003_DescricaoLancamento, T003_Valor, T002_id) VALUES (?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql); //Prepara a string para ser execultado na conexão

        stmt.setString(1, objMovimentacao.getDataLancamento());
        stmt.setString(2, objMovimentacao.getDescricao());
        stmt.setFloat(3, objMovimentacao.getValor());
        stmt.setInt(4, objMovimentacao.getId_conta());
        stmt.execute();
        stmt.close();
    }
    
}

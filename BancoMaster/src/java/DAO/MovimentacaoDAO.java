package DAO;

import BEAN.Conta;
import BEAN.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Movimentacao> consultar(int id_conta) throws SQLException {

        List<Movimentacao> lista = null;

        Connection con = ConexaoDAO.getConexao();
        String sql = "SELECT * FROM T003_MovimentacaoConta WHERE T002_id like '%" + id_conta + "%'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet resultado = stmt.executeQuery();
        lista = new ArrayList<>();
        while (resultado.next()) {
            Movimentacao objMovimentacao = new Movimentacao();

            objMovimentacao.setId(resultado.getInt("T003_id"));
            objMovimentacao.setDataLancamento(resultado.getString("T003_DataLancamento"));
            objMovimentacao.setDescricao(resultado.getString("T003_DescricaoLancamento"));
            objMovimentacao.setValor(resultado.getFloat("T003_Valor"));
            objMovimentacao.setId_conta(resultado.getInt("T002_id"));

            lista.add(objMovimentacao);
        }
        resultado.close();
        stmt.close();
        con.close();
        return lista;
    }
    
}

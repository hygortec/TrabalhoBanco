package DAO;

import BEAN.Conta;
import BEAN.Movimentacao;
import BEAN.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author higor
 */
public class Sacar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter saida = resp.getWriter();

        Movimentacao objMovimentacao = new Movimentacao();
        MovimentacaoDAO objControleMovimentacao = new MovimentacaoDAO();
        Conta objConta = null;
        ContaDAO objControleConta = new ContaDAO();

        int numero = Integer.parseInt(req.getParameter("numero"));
        float valor = Float.parseFloat(req.getParameter("valor"));

        try {
            objConta = objControleConta.consultar(numero);
        } catch (SQLException ex) {
            saida.println(ex);
        }
        if (objConta.getNumero() == numero) {
            objMovimentacao.setDataLancamento("01-01-2016");
            objMovimentacao.setDescricao("Saque");
            objMovimentacao.setId_conta(objConta.getId());
            objMovimentacao.setValor(valor * -1);

            try {
                objControleMovimentacao.movimentar(objMovimentacao);
                saida.println("Saque efetuado");
            } catch (SQLException ex) {
                saida.println(ex);
            }
        }else{
            saida.println("Conta não encontrada");
        }
    }
}

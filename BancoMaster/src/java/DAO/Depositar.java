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
public class Depositar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter saida = resp.getWriter();

        Movimentacao objMovimentacao = new Movimentacao();
        MovimentacaoDAO objControleMovimentacao = new MovimentacaoDAO();
        Conta objConta = null;
        ContaDAO objControleConta = new ContaDAO();

        Sessao sessao = Sessao.getInstance();
        int numero = sessao.getNumero();
        float valor = Float.parseFloat(req.getParameter("valor"));

        try {
            objConta = objControleConta.consultar(numero);
        } catch (SQLException ex) {
            saida.println(ex);
        }

        if (objConta.getNumero() == numero) {
            if (valor <= objConta.getLimite() - objConta.getLimiteAtual()) {
                objConta.setLimiteAtual(objConta.getLimiteAtual() + valor);
                //Deposito pagamento de limite
                objMovimentacao.setDataLancamento("01-01-2016");
                objMovimentacao.setDescricao("Deposito pagamento de limite");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor);
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                saida.println("Deposito efetuado para pagar limite");

            } else if (objConta.getLimiteAtual() < objConta.getLimite()) {
                float aux = valor;
                valor = valor - (objConta.getLimite() - objConta.getLimiteAtual());
                //Deposito pagamento de limite 

                objMovimentacao.setDataLancamento("02-01-2016");
                objMovimentacao.setDescricao("Deposito pagamento de limite");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(aux - valor);
                objConta.setLimiteAtual(objConta.getLimiteAtual()+ (aux-valor));                
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }                
                saida.println("Deposito efetuado para pagar limite");
                //Deposito conta     
                               

                objMovimentacao.setDataLancamento("01-01-2016");
                objMovimentacao.setDescricao("Deposito conta");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor);
                objConta.setSaldo(objConta.getSaldo() + valor);
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                saida.println("Deposito conta");

            } else {
                objConta.setSaldo(objConta.getSaldo() + valor);
                //Deposito conta
                objMovimentacao.setDataLancamento("01-01-2016");
                objMovimentacao.setDescricao("Deposito conta");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor);
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                saida.println("Deposito conta");
            }
            try {
                objControleConta.alterar(objConta);
            } catch (SQLException ex) {
                saida.println(ex);
            }
        } else {
            saida.println("Conta não encontrada");
        }

    }

}

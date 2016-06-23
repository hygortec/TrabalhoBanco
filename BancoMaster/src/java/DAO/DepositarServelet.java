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
import javax.servlet.http.HttpSession;

/**
 *
 * @author higor
 */
public class DepositarServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter saida = resp.getWriter();
        HttpSession session = req.getSession();        
        int numeroConta = (int) session.getAttribute("numero");
        int idPessoa = (int) session.getAttribute("idPessoa");

        Movimentacao objMovimentacao = new Movimentacao();
        MovimentacaoDAO objControleMovimentacao = new MovimentacaoDAO();
        Conta objConta = null;
        ContaDAO objControleConta = new ContaDAO();

        
        
        float valor = Float.parseFloat(req.getParameter("valor"));

        try {
            objConta = objControleConta.consultar(numeroConta);
        } catch (SQLException ex) {
            saida.println(ex);
        }

        if (objConta.getNumero() == numeroConta) {
            if (valor <= objConta.getLimite() - objConta.getLimiteAtual()) {
                objConta.setLimiteAtual(objConta.getLimiteAtual() + valor);
                //Deposito pagamento de limite
                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Deposito pagamento de limite");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor);
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                
                       // new MenssagenRetorno(, 3).getPagina()
                saida.println(new MenssagenRetorno("Deposito efetuado", 3).getPagina());

            } else if (objConta.getLimiteAtual() < objConta.getLimite()) {
                float aux = valor;
                valor = valor - (objConta.getLimite() - objConta.getLimiteAtual());
                //Deposito pagamento de limite 

                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Deposito pagamento de limite");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(aux - valor);
                objConta.setLimiteAtual(objConta.getLimiteAtual()+ (aux-valor));                
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }                
                //saida.println(new MenssagenRetorno("Deposito efetuado para pagar limite", 2).getPagina());
                //Deposito conta                               

                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Deposito conta");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor);
                objConta.setSaldo(objConta.getSaldo() + valor);
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                saida.println(new MenssagenRetorno("Deposito efetuado", 3).getPagina());

            } else {
                objConta.setSaldo(objConta.getSaldo() + valor);
                //Deposito conta
                objMovimentacao.setDataLancamento(new DataHora().getDateTime());//YYYY-MM-DD HH:MM:SS
                objMovimentacao.setDescricao("Deposito conta");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor);
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                saida.println(new MenssagenRetorno("Deposito efetuado", 3).getPagina());
            }
            try {
                objControleConta.alterar(objConta);
            } catch (SQLException ex) {
                saida.println(ex);
            }
        } else {
            saida.println(new MenssagenRetorno("Conta não encontrada", 1).getPagina());
        }

    }

}

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
public class SacarServelet extends HttpServlet {

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
            if (valor > (objConta.getSaldo() + objConta.getLimiteAtual())) {
                saida.println(new MenssagenRetorno("Sem saldo e sem limite", 2).getPagina());
            } else if (valor <= objConta.getSaldo()) {
                objConta.setSaldo(objConta.getSaldo() - valor);

                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Saque conta");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor * -1);
                saida.println(new MenssagenRetorno("Saque efetuado", 3).getPagina());

                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
                
            } else if (valor <= objConta.getLimiteAtual() && objConta.getSaldo() == 0) {
                objConta.setLimiteAtual(objConta.getLimiteAtual() - valor);

                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Saque limite");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor * -1);
                saida.println(new MenssagenRetorno("Saque efetuado", 3).getPagina());
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);
                } catch (SQLException ex) {
                    saida.println(ex);
                }
            } else {
                float aux = valor;
                valor -= objConta.getSaldo();
                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Saque conta");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(objConta.getSaldo() * -1);
                objConta.setSaldo(0);

                try {
                    objControleMovimentacao.movimentar(objMovimentacao);                    
                } catch (SQLException ex) {
                    saida.println(ex);
                }

                objConta.setLimiteAtual(objConta.getLimiteAtual() - valor);
                objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                objMovimentacao.setDescricao("Saque limite");
                objMovimentacao.setId_conta(objConta.getId());
                objMovimentacao.setValor(valor * -1);
                saida.println(new MenssagenRetorno("Saque efetuado", 3).getPagina());
                try {
                    objControleMovimentacao.movimentar(objMovimentacao);                    
                } catch (SQLException ex) {
                    saida.println(ex);
                }
            }
            try {
                objControleConta.alterar(objConta);
            } catch (SQLException ex) {
                saida.println(ex);
            }
        }
    }
}

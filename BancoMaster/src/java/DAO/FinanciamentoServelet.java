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
public class FinanciamentoServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        HttpSession session = req.getSession();
        int numeroConta = (int) session.getAttribute("numero");
        int idPessoa = (int) session.getAttribute("idPessoa");

        float valorTotal = Float.parseFloat(req.getParameter("valorTotal"));
        float valorEntrada = Float.parseFloat(req.getParameter("valorEntrada"));
        int prazo = Integer.parseInt(req.getParameter("prazo"));

        Conta objConta = null;
        ContaDAO objControleConta = new ContaDAO();

        try {
            objConta = objControleConta.consultar(numeroConta);
        } catch (SQLException ex) {
            saida.println(ex);
        }
        Pessoa objPessoa = null;
        PessoaDAO objControlePessoa = new PessoaDAO();
        try {
            objPessoa = objControlePessoa.consultarId(idPessoa);
        } catch (SQLException ex) {
            saida.println(ex);
        }

        if (!objPessoa.equals(null) && objConta.getNumero() == numeroConta) {

            Movimentacao objMovimentacao = new Movimentacao();
            MovimentacaoDAO objControleMovimentacao = new MovimentacaoDAO();

            if (objConta.getSaldo() > 0) {
                if (valorEntrada <= objConta.getSaldo()) {
                    if (prazo <= 420) {
                        if (new CalculaFinanciamento(prazo, valorTotal, valorEntrada).valorPrestacao() > objPessoa.getRendaMensal() * 0.3) {
                            objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                            objMovimentacao.setDescricao("Entrada financiamento");
                            objMovimentacao.setId_conta(objConta.getId());
                            objMovimentacao.setValor(valorEntrada);
                            objConta.setSaldo(objConta.getSaldo()-valorEntrada);
                            try {
                                objControleMovimentacao.movimentar(objMovimentacao);
                            } catch (SQLException ex) {
                                saida.println(ex);
                            }                            
                            objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                            objMovimentacao.setDescricao("Financiamento de "+(valorTotal-valorEntrada)+ " em " + prazo + " vezes");
                            objMovimentacao.setId_conta(objConta.getId());
                            objMovimentacao.setValor(valorTotal-valorEntrada);
                            try {
                                objControleMovimentacao.movimentar(objMovimentacao);
                            } catch (SQLException ex) {
                                saida.println(ex);
                            }
                            objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                            objMovimentacao.setDescricao("Transferencia de financiamento abitacional");
                            objMovimentacao.setId_conta(objConta.getId());
                            objMovimentacao.setValor((valorTotal-valorEntrada)*-1);
                            objConta.setFinanciamento(valorTotal-valorEntrada);
                            objConta.setPrazoFinanciamento(prazo);
                            try {
                                objControleMovimentacao.movimentar(objMovimentacao);
                            } catch (SQLException ex) {
                                saida.println(ex);
                            }
                            try {
                                objControleConta.alterar(objConta);
                            } catch (SQLException ex) {
                                saida.println(ex);
                            }
                            saida.println(new MenssagenRetorno("Financiamento realizado", 3).getPagina());
                        }

                    } else {
                        saida.println(new MenssagenRetorno("O prazo limite é 420", 3).getPagina());
                    }
                } else {
                    saida.println(new MenssagenRetorno("sem valor de entrada!", 3).getPagina());
                }
            } else {
                saida.println(new MenssagenRetorno("Sem saldo para solicitar financiamento", 3).getPagina());
            }
        }
    }
}

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
public class EmprestimoServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        HttpSession session = req.getSession();
        int numeroConta = (int) session.getAttribute("numero");
        int idPessoa = (int) session.getAttribute("idPessoa");

        float valor = Float.parseFloat(req.getParameter("valor"));
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
                if (valor <= objPessoa.getRendaMensal() * 20) {
                    if (prazo <= 48) {
                        objMovimentacao.setDataLancamento(new DataHora().getDateTime());
                        objMovimentacao.setDescricao("Emprestimo em "+prazo+" vezes");
                        objMovimentacao.setId_conta(objConta.getId());
                        objMovimentacao.setValor(valor);
                        objConta.setSaldo(objConta.getSaldo() + valor);
                        objConta.setEmprestimos(valor);
                        objConta.setPrazoEmprestimos(prazo);
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
                        saida.println(new MenssagenRetorno("Emprestimo realizado", 3).getPagina());
                    } else {
                        saida.println(new MenssagenRetorno("O prazo limite é 48", 3).getPagina());
                    }
                } else {
                    saida.println(new MenssagenRetorno("Valor ultrapassa 20 vezes seua renda!", 3).getPagina());
                }
            } else {
                saida.println(new MenssagenRetorno("Sem saldo para solicitar emprestimo", 3).getPagina());
            }
        }
    }
}

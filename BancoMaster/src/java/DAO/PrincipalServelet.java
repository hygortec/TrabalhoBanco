package DAO;

import BEAN.Conta;
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
public class PrincipalServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        HttpSession session = req.getSession();
        int numeroConta = (int) session.getAttribute("numero");
        int idPessoa = (int) session.getAttribute("idPessoa");

        String pagina = "";
        PessoaDAO objControlePessoa = new PessoaDAO();
        Pessoa objPessoa = null;

        try {
            objPessoa = objControlePessoa.consultarId(idPessoa);
        } catch (SQLException ex) {
            saida.print(ex);
        }

        Conta objConta = null;
        ContaDAO objControleConta = new ContaDAO();

        try {
            objConta = objControleConta.consultar(numeroConta);
        } catch (SQLException ex) {
            saida.println(ex);
        }
        float montante = (float) (objConta.getEmprestimos() * (float) Math.pow((1 + 0.07), (objConta.getPrazoEmprestimos() / 12)));

        pagina = ""
                + "<html>"
                + "    <head>"
                + "        <title>Banco Master</title>"
                + "        <meta name='description' content='Tela principal do banco'>"
                + "        <meta charset='utf-8'>"
                + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                + "    </head>"
                + "    <body>"
                + "            <h2>"
                + "                 Bem Vindo! <br>"
                + "                 Sr " + objPessoa.getNome() + "</h2>"
                + "                  <br><br>"
                                + "                  <table border='1'  cellspacing='500' cellpadding='2' align='center' >"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Saldo: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + objConta.getSaldo() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Limite Atual: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + objConta.getLimiteAtual() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                  </table>"
                + "                  <br><br>"
                + "                  <table border='1'  cellspacing='500' cellpadding='2' align='center' >"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Emprestimos: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + objConta.getEmprestimos() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Valor a pagar: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + montante + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Prazo: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + objConta.getPrazoEmprestimos() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                  </table>"
                + "                  <br><br>"
                + "                  <table border='1'  cellspacing='500' cellpadding='2' align='center' >"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Finaciamentos: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + objConta.getFinanciamento() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Montante: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + new CalculaFinanciamento(objConta).getMontante() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                     <tbody>"
                + "                         <tr >"
                + "                             <td width='300' bgcolor='Pink'>" + "Prazo: " + "</td>"
                + "                             <td width='500' bgcolor='Pink'>" + objConta.getPrazoFinanciamento() + "</td>"
                //+ "                             <td width='100' bgcolor='Pink'>" + "" + "</td>"
                + "                         </tr>"
                + "                     </tbody>"
                + "                  </table>"
                + "    </body>"
                + "</html>";
        saida.print(pagina);
    }

}

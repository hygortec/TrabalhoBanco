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

/**
 *
 * @author higor
 */
public class Principal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        Sessao sessao = Sessao.getInstance();
        String pagina = "";
        ContaDAO objControleConta = new ContaDAO();
        Conta objConta = null;
        PessoaDAO objControlePessoa = new PessoaDAO();
        Pessoa objPessoa = null;

        try {
            objConta = objControleConta.consultar(sessao.getNumero());
            objPessoa = objControlePessoa.consultarId(objConta.getId_pessoa());
        } catch (SQLException ex) {
            saida.print(ex);
        }
        
        pagina = ""
                + "<html>"
                + "    <head>"
                + "        <title>Banco Master</title>"
                + "        <meta name='description' content='Tela principal do banco'>"
                + "        <meta charset='utf-8'>"
                + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                + "    </head>"
                + "    <body>"
                + "            <h2><br><br>"
                + "                 Bem Vindo! <br>"
                + "                 Sr "+ objPessoa.getNome()+"</h2>"
                + "    </body>"
                + "</html>";
        saida.print(pagina);
    }

}

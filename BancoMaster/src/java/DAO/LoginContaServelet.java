package DAO;

import BEAN.Conta;
import BEAN.Pessoa;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
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
public class LoginContaServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Conta objConta = null;
        PrintWriter saida = resp.getWriter();

        int agencia = Integer.parseInt(req.getParameter("numeroAgencia"));
        int numero = Integer.parseInt(req.getParameter("numeroConta"));
        String senha = req.getParameter("senha");

        ContaDAO objControleConta = new ContaDAO();

        try {
            objConta = objControleConta.consultar(numero);
        } catch (SQLException ex) {
            saida.println(ex);
        }
        if (!objConta.equals(null)) {
            if (objConta.getNumero() == numero) {

                if (objConta.getAgencia() == agencia) {
                    if (objConta.getSenha().equals(senha)) {

                        session.setAttribute("numero", objConta.getNumero());
                        session.setAttribute("idPessoa", objConta.getId_pessoa());

                        resp.sendRedirect("Home.html");
                    } else {
                        saida.println(new MenssagenRetorno("Senha errada", 4,"Login.html").getPagina());
                    }
                } else {
                    saida.println(new MenssagenRetorno("Agencia não confere", 4,"Login.html").getPagina());

                }

            } else {
                saida.println(new MenssagenRetorno("Conta não confere", 4,"Login.html").getPagina());
            }
        } else {
            saida.println(new MenssagenRetorno("Conta não confere", 4,"Login.html").getPagina());
        }
    }

}

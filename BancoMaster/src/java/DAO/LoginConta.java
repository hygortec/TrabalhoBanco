package DAO;

import BEAN.Conta;
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
public class LoginConta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Conta objConta = new Conta();
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

        if (objConta.getAgencia() == agencia) {
            if (objConta.getSenha().equals(senha)) {
                resp.sendRedirect("Principal.html");
            } else {
                saida.println("Senha errada");
                saida.println(objConta.getSenha());
            }
        } else {
            saida.println("Agencia não confere");
        }

    }

}

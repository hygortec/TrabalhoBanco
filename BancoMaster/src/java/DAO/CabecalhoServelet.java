/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author higor
 */
public class CabecalhoServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        HttpSession session = req.getSession();        
        int numeroConta = (int) session.getAttribute("numero");
        
        String pagina = "";
        pagina += "<!DOCTYPE html>"
                + "<html> "
                + "    <head>"
                + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                + "        <img src='images\\teste2.png' height='150' width='400' border='0' alt='Descrição do logotipo do site'>"
                + "</html>";
        saida.print(pagina);
    }

}

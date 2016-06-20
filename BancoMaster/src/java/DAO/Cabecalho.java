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

/**
 *
 * @author higor
 */
public class Cabecalho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        Sessao sessao = Sessao.getInstance();
        String pagina = "";
        pagina += "<!DOCTYPE html>"
                + "<html> "
                + "    <head>"
                + "        <title>Banco Master</title>"
                + "        <meta name='description' content='Tela principal do banco'>"
                + "        <meta charset='utf-8'>"
                + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                + "    </head>"
                + "    <body>"
                + "        <h1>Banco Master</h1>"
                + "        <h2><p align='Left'>" + "Conta: " + sessao.getNumero() + "</p></h2>"
                + "    </body>"
                + "</html>";
        saida.print(pagina);
    }

}

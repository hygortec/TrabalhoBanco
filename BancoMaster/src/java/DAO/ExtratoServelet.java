/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BEAN.Conta;
import BEAN.Movimentacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class ExtratoServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        HttpSession session = req.getSession();        
        int numeroConta = (int) session.getAttribute("numero");
        int idPessoa = (int) session.getAttribute("idPessoa");
        
        String pagina = "";
        List<Movimentacao> listaMovimentacoes = null;
        Conta objConta = null;
        
        //Pega os dados do banco da conta logada
        ContaDAO objControleConta = new ContaDAO();
        try {
            objConta = objControleConta.consultar(numeroConta);
        } catch (SQLException ex) {
            saida.println(ex);
        }

        MovimentacaoDAO objControleMovimentacao = new MovimentacaoDAO();

        try {
            listaMovimentacoes = objControleMovimentacao.consultar(objConta.getId());
        } catch (SQLException ex) {
            saida.println(ex);
        }

        if (listaMovimentacoes != null && listaMovimentacoes.size() > 0) {

            pagina = "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<title>Banco Master</title>"
                    + "<meta name='description' content='Tela principal do banco'>"
                    + "        <meta charset='UTF-8'>"
                    + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                    + "    </head>"
                    + "    <body> "
                    + "        <article>"
                    + "            <br><br>"
                    + "            <h2>Extrato</h2>   "
                    + "            <form method='POST' action='#'>"
                    + "                <ul>"
                    + "                    <li>"
                    + "                        <table border='1'  cellspacing='500' cellpadding='2' align='center' >"
                    + "                            <thead>"
                    + "                                <tr>"
                    + "                                    <th width='300'>Data</th>"
                    + "                                    <th width='1000'>Descri&ccedil&atildeo</th>"
                    + "                                    <th width='100'>Valor</th>"
                    + "                                </tr>"
                    + "                            </thead>";

            for (int i = 0; i < listaMovimentacoes.size(); i++) {

                if (i % 2 == 0) {
                    pagina += "                     <tbody>"
                            + "                                <tr >"
                            + "                                    <td width='300' bgcolor='Pink'>" + new DataHora().getDataTimePT_br(listaMovimentacoes.get(i).getDataLancamento()) + "</td>"
                            + "                                    <td width='1000' bgcolor='Pink'>" + listaMovimentacoes.get(i).getDescricao() + "</td>"
                            + "                                    <td width='100' bgcolor='Pink' align='Right'>" + listaMovimentacoes.get(i).getValor() + "</td>"
                            + "                                </tr>"
                            + "                            </tbody>";
                }else{
                     pagina += "                     <tbody>"
                            + "                                <tr >"
                            + "                                    <td>" + new DataHora().getDataTimePT_br(listaMovimentacoes.get(i).getDataLancamento()) + "</td>"
                            + "                                    <td>" + listaMovimentacoes.get(i).getDescricao() + "</td>"
                            + "                                    <td align='Right'>" + listaMovimentacoes.get(i).getValor() + "</td>"
                            + "                                </tr>"
                            + "                            </tbody>";
                }

            }
            pagina +="                            <thead>"
                    + "                                <tr border='1' bgcolor='Violet'>"
                    + "                                    <th width='300'></th>"
                    + "                                    <th width='1000'>Saldo Atual</th>";
                    if(objConta.getSaldo() <= 0){
                        pagina +="                      <th width='100'>"+(objConta.getLimite()-objConta.getLimiteAtual())*-1+"</th>";
                    }else{
                        pagina +="                      <th width='100'>"+objConta.getSaldo()+"</th>";
                    }
                    pagina +="                         </tr>"
                    + "                            </thead>"
                    + "                            <thead>"
                    + "                                <tr border='1' bgcolor='Violet'>"
                    + "                                    <th width='300'></th>"
                    + "                                    <th width='1000'>Limite Atual</th>"
                    + "                                    <th width='100'>"+objConta.getLimiteAtual()+"</th>"
                    + "                                </tr>"
                    + "                            </thead>"
                    +"                        </table>"
                    + "                    </li>"
                    + "                </ul>"
                    + "                <p align='center'>"
                    + "                    <button type='submit' class=''>Visualizar</button>"
                    + "                    <button type='reset' class=''>Limpar</button>"
                    + "                </p>"
                    + "        </article>"
                    + "    </body>"
                    + "</html>";
                    saida.println(pagina);
        }else{
            saida.println(new MenssagenRetorno("Sem movimento",3).getPagina());
        }      
    }
}

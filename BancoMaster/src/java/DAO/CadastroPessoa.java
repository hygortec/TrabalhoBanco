package DAO;

import BEAN.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author higor
 */
@WebServlet(name = "CadastroPessoa", urlPatterns = {"/CadastroPessoa"})
public class CadastroPessoa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {      
        
        PrintWriter saida = resp.getWriter();
        
        Pessoa objPessoa = new Pessoa();
        
        objPessoa.setNome(req.getParameter("nome"));
        objPessoa.setEndereco(req.getParameter("endereco"));
        objPessoa.setCpf(req.getParameter("cpf"));
        objPessoa.setRg(req.getParameter("rg"));
        objPessoa.setTelefone(req.getParameter("telefone"));
        objPessoa.setSexo(Integer.parseInt(req.getParameter("radioSexo")));
        objPessoa.setRendaMensal(Float.parseFloat(req.getParameter("rendaMensal")));        
        objPessoa.setTipoPessoa(Integer.parseInt(req.getParameter("radioTipoPessoa")));
        String senha = req.getParameter("senha");
        
        PessoaDAO objControlePessoa = new PessoaDAO();
        ContaDAO objControleConta = new ContaDAO();
        
        try {
            objControlePessoa.cadastrar(objPessoa);
            saida.println("Cadastrado");
        } catch (SQLException ex) {
            saida.println(ex);
        }   
        try {
            objPessoa = objControlePessoa.consultar(objPessoa.getCpf());            
        } catch (SQLException ex) {
            saida.println(ex);
        }
        try {
            objControleConta.cadastrar(objControleConta.gerarNovaConta(objPessoa, senha));
            resp.sendRedirect("Login.html");
        } catch (SQLException ex) {
            saida.println(ex);
        }
                
    }      
}

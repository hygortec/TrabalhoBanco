package DAO;

/**
 *
 * @author higor
 */
public class MenssagenRetorno {

    private String pagina;

    public MenssagenRetorno(String mensagem, int tipo) {
        this.constroiPagina(mensagem, tipo);
    }
    public MenssagenRetorno(String mensagem, int tipo, String url) {
        this.constroiPagina(mensagem, tipo, url);
    }

    private void constroiPagina(String mensagem, int tipo, String url) {
        this.pagina = "<!DOCTYPE html>"
                + "<html> "
                + "<head>"
                + "<title>Banco Master</title>"
                + "<meta name='description' content='Tela principal do banco'>"
                + "        <meta charset='utf-8'>"
                + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                + "    </head>"
                + "    <body>        "
                + "        <article>"
                + "            <h2>Menssagem</h2>"
                + "                 <li>"
                + "                     <script>"
                + "                       alert('" + mensagem + "');"
                + "                        location='" + url + "';"
                + "                      </script>"
                + "                  </li>"
                + "        </article>"
                + "    </body>"
                + "</html>";
    }

    private void constroiPagina(String mensagem, int tipo) {

        this.pagina = "<!DOCTYPE html>"
                + "<html> "
                + "<head>"
                + "<title>Banco Master</title>"
                + "<meta name='description' content='Tela principal do banco'>"
                + "        <meta charset='utf-8'>"
                + "        <link rel='stylesheet' type='text/css' href='css/style.css'/>"
                + "    </head>"
                + "    <body>        "
                + "        <article>";

        if (tipo == 1) {
            this.pagina += "<h2>Erro</h2>";
        } else if (tipo == 2) {
            this.pagina += "<h2>Aten&ccedil&atildeo</h2>";
        } else if (tipo == 3) {
            this.pagina += "<h2>Menssagem</h2>";
        }

        this.pagina += ""
                + "                    <li>"
                + "                        <p align='Center'>" + mensagem + "</p>"
                + "                    </li>"
                + "        </article>"
                + "    </body>"
                + "</html>";
    }

    public String getPagina() {
        return pagina;
    }
}

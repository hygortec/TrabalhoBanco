
package DAO;

/**
 *
 * @author higor
 */
public class Sessao {
    // Variável estática que conterá a instancia da classe
    private static Sessao instance;
    private int numero;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {}

    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
    
}
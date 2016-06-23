package BEAN;

/**
 *
 * @author higor
 */
public class Conta {
    
    private int id;
    private int id_pessoa;
    private int numero;
    private int agencia;
    private float limiteAtual;
    private float saldo;
    private String senha;
    private float limite;
    private float emprestimos;
    private int prazoEmprestimos;
    private float financiamento;
    private int prazoFinanciamento;
   

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }   

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public float getLimiteAtual() {
        return limiteAtual;
    }

    public void setLimiteAtual(float limite) {
        this.limiteAtual = limite;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }    

    public float getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(float emprestimos) {
        this.emprestimos = emprestimos;
    }

    public int getPrazoEmprestimos() {
        return prazoEmprestimos;
    }

    public void setPrazoEmprestimos(int prazoEmprestimos) {
        this.prazoEmprestimos = prazoEmprestimos;
    }

    public float getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(float financiamento) {
        this.financiamento = financiamento;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public void setPrazoFinanciamento(int prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
    }
    
    
}

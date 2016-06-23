package DAO;

import BEAN.Conta;

public class CalculaFinanciamento {
    private int prazo;
    private float valorTotal;
    private float valorEntrada;
    private float valorEmprestimo;
    
    public CalculaFinanciamento(int prazo, float valorT, float valorE){
        this.prazo = prazo;
        this.valorTotal = valorT;
        this.valorEntrada = valorE;
        this.valorEmprestimo = valorT - valorE;
        
    }
    public CalculaFinanciamento(Conta objConta){
        this.prazo = objConta.getPrazoFinanciamento();
        this.valorEmprestimo = objConta.getFinanciamento();        
    }
    
    public float getMontante(){
        float montante = 0;
  
        if(this.valorEmprestimo < 100000){
            montante = (float)((this.valorEmprestimo) * (float)Math.pow((1 + 0.08), (this.prazo/12)));
        }else if(this.valorEmprestimo < 200000){
            montante = (float)((this.valorEmprestimo) * (float)Math.pow((1 + 0.11), (this.prazo/12)));
        }else if(this.valorEmprestimo < 500000){
            montante = (float)((this.valorEmprestimo) * (float)Math.pow((1 + 0.17), (this.prazo/12)));
        }else if(this.valorEmprestimo >= 500000){
            montante = (float)((this.valorEmprestimo) * (float)Math.pow((1 + 0.235), (this.prazo/12)));
        }      
        return montante;        
    }
    public float valorPrestacao(){
        return  (getMontante()/this.prazo);
    }
    
}

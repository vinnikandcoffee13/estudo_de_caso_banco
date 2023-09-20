package poo.gestaocaixaeletronico;

import poo.gestaodecontas.Conta;
import poo.gestaodecontas.Terminal;


public class Caixa {
    private Terminal meuTerminal;
    private CadastroContas bdContas;
    private double saldo;

    public Caixa(Terminal terminal, CadastroContas bd) {
        this.meuTerminal = terminal;
        this.bdContas = bd;
    }

    public double consultaSaldo(int numeroDaConta, int senha) {
        Conta conta;
        conta = this.bdContas.buscaConta(numeroDaConta);
        if (conta != null) {
            return conta.verificaSaldo(senha);
        } else {
            return -1;
        }
    }

    public boolean efetuaSaque(int numeroDaConta, double valor, int senha) {
        if (valor < 0 || (valor % 50) != 0 || valor > 500 || valor > this.saldo) {
            return false;
        }
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if (conta == null || !conta.debitaValor(valor, senha, "Saque Automatico")) {
            return false;
        }
            this.liberaCedulas((int) (valor / 50));
            this.saldo -= valor;
            if (this.saldo < 500) {
                this.meuTerminal.setModo(0);
            }
            return true;
        }

    public boolean transfere(int ContaDeOrigem, int ContaDeDestino, double valor, int senha){
        Conta ContaOrigem = bdContas.buscaConta(ContaDeOrigem);
        Conta ContaDestino = bdContas.buscaConta(ContaDeDestino);
        if(ContaOrigem == null || ContaDestino == null){
           return false;
        }
        else{
            ContaOrigem.debitaValor(valor, ContaDeOrigem, senha +" DÉBITO DE TRANSFERÊNCIA");
            ContaDestino.creditaValor(valor, ContaDeDestino + " CRÉDITO DE TRANSFERÊNCIA");
        }  
        return true;
    }
    public void recarrega() {
        this.saldo = 2000;
        this.meuTerminal.setModo(1);
    }

    private void liberaCedulas(int quantidade) {
        while (quantidade-- > 0) {
            System.out.println("===/ R$50,00 /===");
        }
    }
    
    public boolean depositoDinheiro(int numeroDaConta, double valor){
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if (conta == null || !conta.creditaValor(valor, "Deposito Automatico")) {
            return false;
        }
            return true;
        
    }
    
    public boolean depositoCheque(int numeroDaConta, double valor){
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if (conta == null || !conta.creditaValor(valor, "Deposito Automatico")) {
            return false;
        }
            return true;
        
    }
    
    public boolean efetuaDeposito(int numeroDaConta, double valor) {
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if (conta == null || !conta.creditaValor(valor, "Deposito Automatico")) {
            return false;
        }
            return true;
    }
    
}

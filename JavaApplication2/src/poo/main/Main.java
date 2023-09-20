package poo.main;

import poo.gestaodecontas.Conta;
import poo.gestaocaixaeletronico.Cliente;
import poo.gestaocaixaeletronico.CadastroContas;
import poo.gestaodecontas.Terminal;

public class Main {
    
    public static void main(String[] args) {
        Cliente clientes[] = {new Cliente ("123", "Maria"),new Cliente("323","joao"),new Cliente("143", "carlos"), new Cliente("121", "joao")};
        CadastroContas bd = new CadastroContas(4);
        
        for(int i = 0; i < clientes.length;i++){
            bd.adicionaConta(new Conta(i+1, clientes[i], 123,1000.0));
        }
        (new Terminal(bd)).iniciaOperacao();
    }  
}

package poo.gestaodecontas;

import poo.gestaocaixaeletronico.CadastroContas;
import poo.gestaocaixaeletronico.Caixa;
import poo.gestaodecontas.HistoricoDeLancamentos;

import java.util.Scanner;

public class Terminal {
    private Caixa meuCaixa;
    private int modoAtual;


    public Terminal(CadastroContas bd) {
        this.meuCaixa = new Caixa(this, bd);
    }

    public void iniciaOperacao() {
    	
    	
        int opcao;
        opcao = this.getOpcao();
        while (opcao != 8) {
            switch (opcao) {
                case 1:
                    double saldo = this.meuCaixa.consultaSaldo(getInt("Numero daConta"), getInt("Senha"));
                    if (saldo != -1) {
                        System.out.println("Saldo Atual: " + saldo);
                    } else {
                        System.out.println("Conta/senha inválida");
                    }
                    break;
                case 2:
                    boolean b = this.meuCaixa.efetuaSaque(getInt("Numero daConta"), (double) getInt("Valor"),getInt("Senha"));
                    if (b) {
                        System.out.println("Retire o dinheiro");
                    } else {
                        System.out.println("Pedido de saque recusado");
                    }
                    break;
                case 3:
                    b = this.meuCaixa.efetuaDeposito(getInt("Numero daConta"), (double) getInt("Valor"));
                    if (b) {
                        System.out.println("Coloque o dinheiro");
                    } else {
                        System.out.println("Pedido de debito recusado");
                    }
                    break;    
                case 4:
                    b = this.meuCaixa.depositoDinheiro(getInt("Numero daConta"), (double) getInt("Valor"));
                    if (b) {
                        System.out.println("Coloque o dinheiro");
                    } else {
                         System.out.println("Pedido de cheque recusado");
                    }
                  break;
                case 5:
                    b = this.meuCaixa.depositoCheque(getInt("Numero daConta"), (double) getInt("Valor"));
                    if (b) {
                        System.out.println("Coloque o dinheiro");
                    } else {
                        System.out.println("Pedido de cheque recusado");
                    }
                break;
                case 6:
                    boolean e = this.meuCaixa.transfere(getInt("NÚMERO DA CONTA DE ORIGEM"), getInt("SENHA DA CONTA DE ORIGEM"), getInt("NÚMERO DA CONTA DE DESTINO"), getInt("VALOR"));
                    if (e == true)  {
                        System.out.println("TRANSFERÊNCIA BANCÁRIA REALIZADA COM SUCESSO");
                    } else  {
                        System.out.println("TRANSFERÊNCIA RECUSADA, TENTE NOVAMENTE MAIS TARDE");
                    }
                break;
                case 7:
                    boolean f = this.meuCaixa.exibeExtrato(getInt("NÚMERO DA CONTA"), getInt("SENHA DA CONTA"));
                    if (f == true)  {
                        System.out.println("EXTRATO BANCÁRIO EMITIDO DE FORMA AUTOMÁTICA");
                    } else  {
			System.out.println("PEDIDO DE EMISSÃO DE EXTRATO RECUSADO, TENTE NOVAMENTE MAIS TARDE");
                    }
		break;
                case 8:
                	this.meuCaixa.recarrega();
                	break;
                case 9:
                	break;
            }
            opcao = getOpcao();
        }
    }
    
    public void setModo(int modo) {
        if (modo == 0 || modo == 1) {
            this.modoAtual = modo;
        }
    }

    private int getOpcao() {
        int opcao;
        do {
            if (this.modoAtual == 1) {
                opcao = getInt("Opcao: 1 - Consulta Saldo, 2 - Saque, 3 - Deposita, 4 - Deposito em dinheiro, 5 - Deposito em cheque, 6 - transfere ,7 - Extrato, 9 - Sair");

                if (opcao != 1 & opcao != 2 & opcao != 3 & opcao != 4 & opcao != 5 & opcao != 6  & opcao != 7 & opcao != 9) {
                    opcao = 0;
                }
            } else {
                opcao = getInt("Opcao: 7 - Extrato, 8 - Recarrega,  9 - Sair");

                if (opcao != 7 & opcao != 8 & opcao != 9) {
                    opcao = 0;
                }
            }
        } while (opcao == 0);
        return opcao;
    }

    private int getInt(String string) {
        Scanner r = new Scanner(System.in);
        System.out.println("Entre com " + string);
        if (r.hasNextInt()) {
            return r.nextInt();
        }
        String st = r.next();
        System.out.println("Erro na Leitura de Dados");
        return 0;
    }
    
    private double getDouble(String string) {
        Scanner r = new Scanner(System.in);
        System.out.println("Entre com " + string);
        if (r.hasNextDouble()) {
            return r.nextDouble();
        }
        String st = r.next();
        System.out.println("Erro na Leitura de Dados");
        return 0;
    }
    
    
}

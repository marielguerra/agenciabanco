package classes;

public class Conta {
    //atributos
    public int numero;
    public String cliente;
    public double saldo;
    public double limite;

    //construtor
    public Conta(int numero, String cliente, double saldo, double limite) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
        this.limite = limite;
    }

    //metodos

    //saca dinheiro da conta do banco
    public boolean sacaDinheiro(double quantidade) {
        if (quantidade <= this.saldo + this.limite) {
            this.saldo -= quantidade;
            return true;
        } else {
            return false;
        }
    }

    //deposita dinheiro na conta
    public void depositaDinheiro(double quantidade){
        this.saldo += quantidade;
    }

    //transfere uma conta para outra
    public boolean transfereDinheiro(Conta destino, double quantidade){
        if (this.sacaDinheiro(quantidade)){
            destino.depositaDinheiro(quantidade);
            return true;
        } else {
            return false;
        }
    }

    public String mostraDados(){
        return "Número da conta : " + this.numero +
                "\nCliente : " + this.cliente +
                "\nSaldo da conta : " + this.saldo +
                "\nLímite da conta : " + this.limite + "\n";
    }


}

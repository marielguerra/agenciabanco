package testes;

import classes.Conta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestaConta {
    public static void main(String[] args) {

        String[] menu = {"Criar conta", "Sacar Dinheiro", "Depositar Dinheiro", "Transferir Dinheiro", "Mostrar Cadastros", "Sair"};
        List<Conta> contas = new ArrayList<>();
        String opcao;

        do {
            opcao = (String) JOptionPane.showInputDialog(null, "Escolha o que você quer fazer?", "MENU CONTA BANCARIA", JOptionPane.PLAIN_MESSAGE, null, menu, "Criar conta");


            switch (opcao) {

                case ("Criar conta"):
                    int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da sua conta : ", "CRIAR CONTA", JOptionPane.INFORMATION_MESSAGE));
                    String cliente = JOptionPane.showInputDialog(null, "Insira o nome do titular da conta : ", "CRIAR CONTA", JOptionPane.INFORMATION_MESSAGE);
                    double saldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o saldo da conta : ", "CRIAR CONTA", JOptionPane.INFORMATION_MESSAGE));
                    double limite = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o límite da conta : ", "CRIAR CONTA", JOptionPane.INFORMATION_MESSAGE));

                    Conta conta = new Conta(numero, cliente, saldo, limite);
                    contas.add(conta);
                    break;

                case ("Sacar Dinheiro"):
                    int numeroContaSaque = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da sua conta :", "SAQUE", JOptionPane.INFORMATION_MESSAGE));
                    double quantidadeSaque = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira a quantidade que você deseja sacar :", "SAQUE", JOptionPane.INFORMATION_MESSAGE));

                    boolean sacado = false;

                    for (Conta contaSaque : contas) {

                        if (numeroContaSaque == contaSaque.numero && quantidadeSaque <= contaSaque.saldo + contaSaque.limite) {
                            contaSaque.sacaDinheiro(quantidadeSaque);
                            sacado = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "NÚMERO DA CONTA INVALIDO!!", "ERRO", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    if (sacado) {
                        JOptionPane.showMessageDialog(null, "SAQUE FEITO COM SUCESSO!!", "SAQUE", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSIVEL REALIZAR O SAQUE!!", "ERRO", JOptionPane.WARNING_MESSAGE);
                    }

                    break;
                case ("Depositar Dinheiro"):
                    int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da sua conta :", "DEPOSITO", JOptionPane.INFORMATION_MESSAGE));
                    double quantidadeDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira a quantidade que você deseja depositar :", "DEPOSITO", JOptionPane.INFORMATION_MESSAGE));


                    for (Conta contaDeposito : contas) {
                        if (numeroConta == contaDeposito.numero) {
                            contaDeposito.depositaDinheiro(quantidadeDeposito);
                            JOptionPane.showMessageDialog(null, "DEPÓSITO FEITO COM SUCESSO!!", "DEPOSITO", JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                    break;
                case ("Transferir Dinheiro"):
                    int numeroContaTransfere = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da sua conta :", "TRANFERENCIA", JOptionPane.INFORMATION_MESSAGE));
                    int numeroContaRecebe = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da conta que irá receber :", "TRANSFERENCIA", JOptionPane.INFORMATION_MESSAGE));
                    double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira a quantidade que você deseja transferir :", "TRANSFERENCIA", JOptionPane.INFORMATION_MESSAGE));
                    boolean achouTransferidor = false;
                    boolean achouRecepitor = false;
                    boolean transferido = false;

                    for (Conta contaTransfere : contas) {
                        if (numeroContaTransfere == contaTransfere.numero && quantidade <= contaTransfere.saldo + contaTransfere.limite) {
                            achouTransferidor = true;
                            for (Conta contaRecebe : contas) {
                                if (numeroContaRecebe == contaRecebe.numero) {
                                    achouRecepitor = true;
                                    contaTransfere.transfereDinheiro(contaRecebe, quantidade);
                                    transferido = true;
                                }
                            }
                        }
                    }
                    if (!achouRecepitor) {
                        JOptionPane.showMessageDialog(null, "NÚMERO DE CONTA RECEPITORA INVÁLIDO!!", "ERRO", JOptionPane.WARNING_MESSAGE);
                    }
                    if (!achouTransferidor) {
                        JOptionPane.showMessageDialog(null, "NÚMERO DE CONTA TRANSFERIDORA INVÁLIDO!!", "ERRO", JOptionPane.WARNING_MESSAGE);
                    }
                    if (transferido) {
                        JOptionPane.showMessageDialog(null, "TRANSFERENCIA FEITA COM SUCESSO!!", "TRANSFERENCIA", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "NÃO FOI POSSIVEL COMPLETAR A TRANSFERENCIA!!", "ERRO", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case ("Mostrar Cadastros"):
                    for (Conta contaMostrar : contas) {
                        System.out.println(contaMostrar.mostraDados());
                    }
                    break;
            }
        } while (opcao != "Sair");
    }
}

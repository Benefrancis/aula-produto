package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.Produto;

import javax.swing.*;
import java.math.BigDecimal;

public class DepositoView {

    public Deposito form() {
        String nome = JOptionPane.showInputDialog("Nome do Deposito");
        return new Deposito(nome);
    }
}

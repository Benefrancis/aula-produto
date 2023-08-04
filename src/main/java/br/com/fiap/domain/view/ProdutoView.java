package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Produto;

import javax.swing.*;
import java.math.BigDecimal;

public class ProdutoView {

    public Produto form() {
        String nome = JOptionPane.showInputDialog("Nome do Produto");
        String descricao = JOptionPane.showInputDialog("Descricao");
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Valor").replace(",", "."));
        return new Produto(nome, descricao, BigDecimal.valueOf(valor));
    }

}

package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.repository.DepositoRepository;
import br.com.fiap.domain.repository.ProdutoRepository;
import br.com.fiap.domain.service.EstoqueService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EstoqueView {

    public List<ProdutoEstocado> formEstocar() {

        List<ProdutoEstocado> produtosEstocados = new ArrayList<>();

        var produtos = ProdutoRepository.findAll();

        var depositos = DepositoRepository.findAll();

        if (produtos.size() > 0 && depositos.size() > 0) {

            Produto produto = (Produto) JOptionPane
                    .showInputDialog(null, "Escolha um produto", "Selecao de produtos",
                            JOptionPane.PLAIN_MESSAGE, null, produtos.toArray(), produtos.get(0));

            if (Objects.isNull(produto)) return produtosEstocados;

            Deposito deposito = (Deposito) JOptionPane
                    .showInputDialog(null, "Escolha o Local", "Selecao de Depósito",
                            JOptionPane.PLAIN_MESSAGE, null, depositos.toArray(), depositos.get(0));

            if (Objects.isNull(deposito)) return produtosEstocados;

            Integer quantidade = 0;

            do {
                quantidade = Integer.valueOf(JOptionPane.showInputDialog("Informe a quantidade de itens que deseja estocar", "0"));
            } while (quantidade <= 0);

            produtosEstocados = EstoqueService.estocar(produto, deposito, Long.valueOf(quantidade));

            if (produtosEstocados.size() > 0)
                JOptionPane.showMessageDialog(null, "Sucesso!\n" + produtosEstocados.size() + " produto(s) estocados com sucesso em " + deposito.getNome());
        } else {
            JOptionPane.showMessageDialog(null, "Não é possível estocar!\nVerifique se existe Produto ou Depósito disponível");
        }

        return produtosEstocados;
    }

}

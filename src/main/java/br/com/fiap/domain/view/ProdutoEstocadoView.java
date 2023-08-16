package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.repository.DepositoRepository;
import br.com.fiap.domain.repository.ProdutoRepository;
import br.com.fiap.domain.service.ProdutoEstocadoService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoEstocadoView {

    ProdutoRepository produtoRepository = new ProdutoRepository();
    DepositoRepository depositoRepository = new DepositoRepository();

    public List<ProdutoEstocado> formEstocar() {

        List<ProdutoEstocado> produtosEstocados = new ArrayList<>();

        List<Produto> produtos = produtoRepository.findAll();

        var depositos = depositoRepository.findAll();

        if (produtos.size() > 0 && depositos.size() > 0) {

            Produto produto = (Produto) JOptionPane
                    .showInputDialog(null, "Escolha um produto", "Selecao de produtos",
                            JOptionPane.QUESTION_MESSAGE, null, produtos.toArray(), produtos.get(0));

            if (Objects.isNull(produto)) return produtosEstocados;

            Deposito deposito = (Deposito) JOptionPane
                    .showInputDialog(null, "Escolha o Local", "Selecao de Depósito",
                            JOptionPane.QUESTION_MESSAGE, null, depositos.toArray(), depositos.get(0));

            if (Objects.isNull(deposito)) return produtosEstocados;

            Integer quantidade = 0;

            do {
                quantidade = Integer.valueOf(JOptionPane.showInputDialog("Informe a quantidade de itens que deseja estocar", "0"));
            } while (quantidade <= 0);

            produtosEstocados = ProdutoEstocadoService.entrada(produto, deposito, Long.valueOf(quantidade));

            if (produtosEstocados.size() > 0)
                JOptionPane.showMessageDialog(null, "Sucesso!\n" + produtosEstocados.size() + " produto(s) estocados com sucesso em " + deposito.getNome());
        } else {
            JOptionPane.showMessageDialog(null, "Não é possível estocar!\nVerifique se existe Produto ou Depósito disponível");
        }

        return produtosEstocados;
    }

}

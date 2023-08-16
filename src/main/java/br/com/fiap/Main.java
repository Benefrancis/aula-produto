package br.com.fiap;

import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.repository.ProdutoRepository;
import br.com.fiap.domain.view.ProdutoEstocadoView;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProdutoEstocadoView view = new ProdutoEstocadoView();
        List<ProdutoEstocado> estocados = view.formEstocar();

        estocados.forEach(System.out::println);


    }
}
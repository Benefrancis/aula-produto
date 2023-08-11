package br.com.fiap;

import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.repository.ProdutoRepository;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        var repo = new ProdutoRepository();
        repo.findByName( "Camisa do Corinthians" ).forEach( System.out::println );

        var produto = new Produto( null, "Camisa Corinthians", "Campeão Brasileiro", BigDecimal.valueOf( 299.99 ) );
        System.out.println( repo.persist( produto ) );

    }
}
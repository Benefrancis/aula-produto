package br.com.fiap;

import br.com.fiap.domain.repository.DepositoRepository;

public class Main {

    public static void main(String[] args) {

//        List<Produto> produtos = ProdutoRepository.findAll();
//
//        for (Produto produto : produtos) {
//            System.out.println( produto );
//        }

//        Produto prod = ProdutoRepository.findById( 8L );
//        System.out.println(prod);

//        List<Produto> produtos = ProdutoRepository.findByName( "ipad" );
//        produtos.forEach( System.out::println );

//        ProdutoView view = new ProdutoView();
//        Produto produto = view.form();
//        var p = ProdutoService.persist(produto);
//        if(Objects.nonNull(p)){
//            System.out.println(p);
//        }else{
//            System.out.printf("Não foi possível salvar o produto");
//        }

        DepositoRepository.findAll().forEach(System.out::println);


    }
}
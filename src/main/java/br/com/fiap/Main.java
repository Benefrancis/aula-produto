package br.com.fiap;

import br.com.fiap.domain.view.ProdutoEstocadoView;

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
//        if (Objects.nonNull(p)) {
//            System.out.println(p);
//        } else {
//            System.out.printf("Não foi possível salvar o produto");
//        }

        //       DepositoRepository.findAll().forEach(System.out::println);


//        var produto = ProdutoRepository.findById(1L); //Produto
//        var xangai = DepositoRepository.findById(1L); //Deposito
//        var osasco = DepositoRepository.findById(12L); //Deposito
//
//        if (Objects.nonNull(produto) && Objects.nonNull(xangai))
//            EstoqueService.estocar(produto, xangai, 1L);
//        if (Objects.nonNull(produto) && Objects.nonNull(osasco))
//            EstoqueService.estocar(produto, osasco, 1L);
//
//        EstoqueRepository repo = new EstoqueRepository();
//        repo.findAll().forEach(System.out::println);


//        EstoqueView estoqueView = new EstoqueView();
//        var itens = estoqueView.formEstocar();
//        itens.forEach(System.out::println);

        ProdutoEstocadoView view = new ProdutoEstocadoView();
        view.formEstocar().forEach( System.out::println );

    }
}
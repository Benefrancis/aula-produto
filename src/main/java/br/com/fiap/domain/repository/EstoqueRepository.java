package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.ProdutoEstocado;

import java.util.ArrayList;
import java.util.List;

public class EstoqueRepository {

    private static List<ProdutoEstocado> itens;

    static {
//        var iphone = ProdutoRepository.findById(1L); //Produto
//        var xangai = DepositoRepository.findById(1L); //Deposito
//        var osasco = DepositoRepository.findById(12L); //Deposito
        itens = new ArrayList<>();
        //Incluindo 200 iphones no itemDoEstoque de Xangai
//        EstoqueService.estocar(iphone, xangai, 2000L);
//        EstoqueService.estocar(iphone, osasco, 100L);
    }

    public List<ProdutoEstocado> findAll() {
        return itens;
    }

    public ProdutoEstocado findById(Long id) {
        return itens.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    public static List<ProdutoEstocado> persist(List<ProdutoEstocado> itensEtiquetados) {
        for (ProdutoEstocado e : itensEtiquetados) {
            long id = (itens.size() + 1);
            e.setId(id);
            itens.add(e);
        }
        return itens;
    }
}

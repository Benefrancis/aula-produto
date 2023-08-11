package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.ProdutoEstocado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoEstocadoRepository {

    private static List<ProdutoEstocado> itens;

    static {
        itens = new ArrayList<>();

        ProdutoEstocado prod1 = new ProdutoEstocado();
        prod1.setId(1L)
                .setDeposito(DepositoRepository.findById(1L))
                .setProduto(ProdutoRepository.findById(1L))
                .setEntrada(LocalDateTime.now())
                .setNumeroDeSerie("00019");

        itens.add(prod1);

    }

    public static List<ProdutoEstocado> findAll() {
        return itens;
    }

    public static ProdutoEstocado findById(Long id) {
        for (ProdutoEstocado p : itens) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static List<ProdutoEstocado> findByProduto(Produto p) {
        List<ProdutoEstocado> encontrados = new ArrayList<>();

        for (ProdutoEstocado pe : itens) {
            if (pe.getProduto().equals(p)) {
                encontrados.add(pe);
            }
        }
        return encontrados;
    }

    public static List<ProdutoEstocado> findByDeposito(Deposito deposito) {
        return itens.stream()
                .filter(pe -> pe.getDeposito().equals(deposito))
                .toList();
    }

    public static List<ProdutoEstocado> findByProdutoAndDeposito(Produto produto, Deposito deposito) {
        return itens.stream()
                .filter(pe -> pe.getDeposito().equals(deposito))
                .filter(pe -> pe.getProduto().equals(produto))
                .toList();
    }

    public static ProdutoEstocado persist(ProdutoEstocado pe) {
        pe.setId(itens.size() + 1L);
        itens.add(pe);
        return pe;
    }

}

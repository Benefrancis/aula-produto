package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoService {

    static ProdutoRepository repo = new ProdutoRepository();

    public static Produto persist(Produto p) {
        if (Objects.isNull(p.getNome())) {
            return null;
        }
        if (Objects.isNull(p.getDescricao()))
            return null;
        if (Objects.isNull(p.getValor()) || p.getValor().compareTo(BigDecimal.ZERO) <= 0)
            return null;
        return repo.persist(p);
    }

}

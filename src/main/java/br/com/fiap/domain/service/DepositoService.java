package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.repository.DepositoRepository;

import java.util.Objects;

public class DepositoService {

    static DepositoRepository repo = new DepositoRepository();

    public Deposito persist(Deposito d) {
        if (Objects.isNull(d.getNome())) return null;
        return repo.persist(d);
    }
}

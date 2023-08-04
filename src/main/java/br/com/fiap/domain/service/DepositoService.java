package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.repository.DepositoRepository;

import java.util.Objects;

public class DepositoService {

    public static Deposito persist(Deposito d) {
        if (Objects.isNull(d.getNome())) return null;
        return DepositoRepository.persist(d);
    }
}

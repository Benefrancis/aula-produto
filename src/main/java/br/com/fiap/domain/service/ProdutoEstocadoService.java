package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.repository.ProdutoEstocadoRepository;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

public class ProdutoEstocadoService {

    public static ProdutoEstocado entrada(ProdutoEstocado pe) {
        if (pe.getProduto().equals(null)) return null;
        if (pe.getDeposito().equals(null)) return null;
        pe.setEntrada(LocalDateTime.now());
        pe.setSaida(null);
        pe.setNumeroDeSerie(gerarNumeroDeSerie(pe));
        return ProdutoEstocadoRepository.persist(pe);
    }

    private static String gerarNumeroDeSerie(ProdutoEstocado pe) {
        Random random = new Random();
        var numeroAleatorio = random.nextInt(10000, 99999);
        return numeroAleatorio + "" + gerarDigito(numeroAleatorio);
    }

    private static String gerarDigito(int numeroAleatorio) {
        var formatador = new DecimalFormat("00");
        Integer valor = String.valueOf(numeroAleatorio).length() - 2;
        var n = valor >> 2;
        return formatador.format(n);
    }
}

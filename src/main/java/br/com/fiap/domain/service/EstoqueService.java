package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.repository.EstoqueRepository;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EstoqueService {

    public static List<ProdutoEstocado> estocar(Produto produto, Deposito deposito, Long quantidade) {
        long qtd = 0;
        List<ProdutoEstocado> etiquetados = new ArrayList<>();
        while (qtd < quantidade) {
            var entrada = LocalDateTime.now();
            var numeroDeSerie = gerarNumeroDeSerie(produto, deposito);
            ProdutoEstocado produtoEstocado = new ProdutoEstocado(null, produto, deposito, entrada, null, numeroDeSerie);
            etiquetados.add(produtoEstocado);
            qtd++;
        }
        return EstoqueRepository.persist(etiquetados);
    }

    private static String gerarNumeroDeSerie(Produto produto, Deposito deposito) {

        var agora = LocalDateTime.now();

        var a = agora.getYear();
        var m = agora.getMonth().getValue();
        var d = agora.getDayOfMonth();
        var h = agora.getHour();
        var min = agora.getMinute();
        var seg = agora.getSecond();
        var ano = String.valueOf(a);

        //Usando decimal format para completar com zero a esquerda caso o número tenha apenas um dígito
        var doisDigitos = new DecimalFormat("00");

        var mes = doisDigitos.format(m);
        var dia = doisDigitos.format(d);
        var hora = doisDigitos.format(h);
        var minuto = doisDigitos.format(min);
        var segundo = doisDigitos.format(seg);

        var seisDigitos = new DecimalFormat("000000");
        String codProduto = seisDigitos.format(produto.getId());
        String codDeposito = seisDigitos.format(deposito.getId());

        Random random = new Random();
        //Número aleatório entre 1000 e 9999
        var numeroAleatorio = random.nextInt(1000, 9999);
        var numeroDeSerieSemDigito = ano + mes + dia + hora + minuto + segundo + codDeposito + codProduto + numeroAleatorio;
        return numeroDeSerieSemDigito + gerarDigito(numeroDeSerieSemDigito);
    }

    private static String gerarDigito(String numeroDeSerieSemDigito) {
        var doisDigitos = new DecimalFormat("00");
        //A variável valor será alimentada com os dois últimos dígitos
        var valor = Integer.valueOf(numeroDeSerieSemDigito.substring(numeroDeSerieSemDigito.length() - 2));
        //O operador >> desloca duas casas para a direita do número binário obtido pelo valor
        var n = (valor >> 2);
        return doisDigitos.format(n);
    }
}

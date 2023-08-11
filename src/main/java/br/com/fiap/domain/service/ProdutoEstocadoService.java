package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.repository.ProdutoEstocadoRepository;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ProdutoEstocadoService {


    public static List<ProdutoEstocado> entrada(Produto produto, Deposito deposito, Long qtd) {
        //Lista para retorno
        List<ProdutoEstocado> lista = new ArrayList<>();

        long cont = 0L;
        while (cont < qtd) {
            var pe = new ProdutoEstocado();
            pe.setProduto( produto ).setDeposito( deposito );
            pe = entrada( pe );
            if (Objects.nonNull( pe )) lista.add( pe );
            cont++;
        }

        return lista;

    }


    private static ProdutoEstocado entrada(ProdutoEstocado pe) {
        if (pe.getProduto().equals( null )) return null;
        if (pe.getDeposito().equals( null )) return null;
        pe.setEntrada( LocalDateTime.now() );
        pe.setSaida( null );
        pe.setNumeroDeSerie( gerarNumeroDeSerie( pe ) );
        return ProdutoEstocadoRepository.persist( pe );
    }

    private static String gerarNumeroDeSerie(ProdutoEstocado pe) {
        Random random = new Random();
        var numeroAleatorio = random.nextInt( 10000, 99999 );
        return numeroAleatorio + "-" + gerarDigito( numeroAleatorio );
    }

    private static String gerarDigito(int numeroAleatorio) {
        //Nas três próximas linhas eu garanto que estou pegando os dois últimos dígitos
        //do número informado, mesmo que o número tenha 1 dígito, pois o formatador
        // colocou uma casa a esqueda neste caso.
        var formatador = new DecimalFormat( "00" );
        var s = formatador.format( numeroAleatorio );
        var n = Integer.valueOf( s.substring( s.length() - 2 ) );
        //Binário
        String x = Integer.toBinaryString( n );
        //Binário após deslocar uma casa para direita com o operador >>
        String y = Integer.toBinaryString( n >> 2 );
        //Gerando o dígito:
        var digito = formatador.format( n >> 2 );
        //  System.out.println( n + ": x -> " + x + " y -> " + y + " Dígito: " + digito );
        return digito;
    }


}

package br.com.fiap.domain.repository.collection;

import br.com.fiap.domain.entity.Deposito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepositoCollectionRepository {

    private static List<Deposito> depositos;

    static {
        Deposito xangai = new Deposito(1L, "Xangai");
        Deposito buenosAires = new Deposito(2L, "Buenos Aires");
        Deposito barcelona = new Deposito(3L, "Barcelona");
        Deposito caracas = new Deposito(4L, "Caracas");
        Deposito quebec = new Deposito(5L, "Quebec");
        Deposito compton = new Deposito(6L, "Compton");
        Deposito paraguai = new Deposito(7L, "Paraguai");
        Deposito merces = new Deposito(8L, "Mercês");
        Deposito pelourinho = new Deposito(9L, "Pelourinho");
        Deposito joanesburgo = new Deposito(10L, "Joanesburgo");
        Deposito dakar = new Deposito(11L, "Dakar");
        Deposito osasco = new Deposito(12L, "Osasco");
        Deposito pelotas = new Deposito(13L, "Pelotas");
        depositos = new ArrayList<>();
        depositos.addAll(Arrays.asList(xangai, buenosAires, barcelona, caracas, quebec, compton, paraguai, merces, pelourinho, joanesburgo, dakar, osasco, pelotas));
    }

    public static List<Deposito> findAll() {
        return depositos;
    }

    public static Deposito findById(Long id) {
        for (Deposito d : depositos) {
            if (d.getId().equals(id)) return d;
        }
        return null;
    }

    public static List<Deposito> findByName(String texto) {
        return depositos.stream().filter(deposito -> deposito.getNome().equalsIgnoreCase(texto)).toList();
    }

    public static Deposito persist(Deposito d){
        d.setId(depositos.size()+1L);
        depositos.add(d);
        return d;
    }

}

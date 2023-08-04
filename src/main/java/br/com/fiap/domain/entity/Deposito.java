package br.com.fiap.domain.entity;

public class Deposito {

    private Long id;

    private String nome;

    public Deposito(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Deposito(String nome) {
        this.nome = nome;
    }

    public Deposito() {
    }

    public Long getId() {
        return id;
    }

    public Deposito setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Deposito setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "Deposito{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}

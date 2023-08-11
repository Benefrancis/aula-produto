package br.com.fiap.domain.repository.abstracao;

import java.util.Collection;

public interface Repository<T, U> {

    public Collection<T> findAll();

    public T findById(U id);

    public Collection<T> findByName(String texto);

    public T persist(T t);

    public T update(T t);

    public void delete(T t);

}

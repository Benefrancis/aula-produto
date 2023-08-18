package br.com.fiap.domain.repository.abstracao;

import br.com.fiap.domain.repository.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T, U> {

    default Connection open() throws SQLException {
        return ConnectionFactory.build().createConnection();
    }

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByName(String texto);

    public T persist(T t);

    public T update(T t);

    public void delete(T t);

}

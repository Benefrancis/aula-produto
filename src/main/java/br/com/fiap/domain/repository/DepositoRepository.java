package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.repository.abstracao.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositoRepository implements Repository<Deposito, Long> {

    public DepositoRepository() {
        super();
    }

    @Override
    public List<Deposito> findAll() {

        var clazz = Deposito.class.getSimpleName().toUpperCase();

        List<Deposito> retorno = new ArrayList<>();

        String sql = "SELECT * from " + clazz;

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            ps = open().prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retorno.add(new Deposito(rs.getLong("ID"), rs.getString("NOME")));
                }
            } else {
                System.out.println("Não temos " + clazz + " cadastrado no banco de dados");
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar os depositos: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Erro ao tentar fechar o Statment ou o ResultSet de " + clazz);
            }
        }
        return retorno;
    }

    @Override
    public Deposito findById(Long id) {

        var clazz = Deposito.class.getSimpleName().toUpperCase();

        String sql = "SELECT * from " + clazz + " where id  =?";

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            ps = open().prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    return new Deposito(rs.getLong("ID"), rs.getString("NOME"));
                }
            } else {
                System.out.println(clazz + " nao encontrado");
            }

            return null;

        } catch (SQLException e) {
            System.out.println("Não foi possível consultar o " + clazz + ": " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Erro ao tentar fechar o Statment ou o ResultSet de " + clazz);
            }
        }

        return null;
    }

    @Override
    public List<Deposito> findByName(String texto) {
        return null;
    }


    @Override
    public Deposito persist(Deposito deposito) {

        var clazz = Deposito.class.getSimpleName().toUpperCase();

        String sql = "INSERT INTO " + clazz + " (NOME) VALUES (?)";

        PreparedStatement ps = null;

        ResultSet rs = null;

        System.out.println(sql);

        try {
            ps = open().prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setString(1, deposito.getNome().toUpperCase());

            ps.execute();

            rs = ps.getGeneratedKeys();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    deposito.setId(rs.getLong(1));
                }
                return deposito;
            } else {
                System.out.println(clazz + " nao encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar " + clazz + " no banco de dados: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Erro ao tentar fechar o Statement de " + clazz);
            }
        }

        return null;
    }

    @Override
    public Deposito update(Deposito deposito) {
        return null;
    }

    @Override
    public void delete(Deposito deposito) {

    }
}

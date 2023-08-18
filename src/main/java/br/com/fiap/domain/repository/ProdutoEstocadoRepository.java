package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Deposito;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.ProdutoEstocado;
import br.com.fiap.domain.repository.abstracao.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoEstocadoRepository implements Repository<ProdutoEstocado, Long> {

    ProdutoRepository produtoRepository = new ProdutoRepository();
    DepositoRepository depositoRepository = new DepositoRepository();


    @Override
    public List<ProdutoEstocado> findAll() {

        var clazz = "PRODUTO_ESTOCADO";

        List<ProdutoEstocado> retorno = new ArrayList<>();

        String sql = "SELECT * from " + clazz;

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            ps = open().prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Produto p = produtoRepository.findById(rs.getLong("ID_PRODUTO"));
                    Deposito d = depositoRepository.findById(rs.getLong("ID_DEPOSITO"));
                    LocalDateTime e = LocalDateTime.ofInstant(rs.getDate("ENTRADA").toInstant(), ZoneId.of("UTC"));
                    LocalDateTime s = LocalDateTime.ofInstant(rs.getDate("SAIDA").toInstant(), ZoneId.of("UTC"));
                    retorno.add(new ProdutoEstocado(rs.getLong("ID"), p, d, e, s, rs.getString("NR_SERIE")));
                }
            } else {
                System.out.println("Não temos " + clazz + " cadastrado no banco de dados");
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar os Produtos Estocados: " + e.getMessage());
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
    public ProdutoEstocado findById(Long id) {

        var clazz = "PRODUTO_ESTOCADO";

        ProdutoEstocado retorno = null;

        String sql = "SELECT * from " + clazz + " where id = ?";

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {

            ps = open().prepareStatement(sql);

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Produto p = produtoRepository.findById(rs.getLong("ID_PRODUTO"));
                    Deposito d = depositoRepository.findById(rs.getLong("ID_DEPOSITO"));
                    LocalDateTime e = LocalDateTime.ofInstant(rs.getDate("ENTRADA").toInstant(), ZoneId.of("UTC"));
                    LocalDateTime s = LocalDateTime.ofInstant(rs.getDate("SAIDA").toInstant(), ZoneId.of("UTC"));
                    retorno = new ProdutoEstocado(rs.getLong("ID"), p, d, e, s, rs.getString("NR_SERIE"));
                }
            } else {
                System.out.println("Não temos " + clazz + " cadastrado no banco de dados");
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar os Produtos Estocados: " + e.getMessage());
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
    public List<ProdutoEstocado> findByName(String texto) {

        var clazz = "PRODUTO_ESTOCADO";

        List<ProdutoEstocado> retorno = new ArrayList<>();

        String sql = "SELECT pe.* from " + clazz + " pe inner join PRODUTO p where UPPER(p.nome) like %?%";

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {

            ps = open().prepareStatement(sql);

            ps.setString(1, texto.toUpperCase());

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Produto p = produtoRepository.findById(rs.getLong("ID_PRODUTO"));
                    Deposito d = depositoRepository.findById(rs.getLong("ID_DEPOSITO"));
                    LocalDateTime e = LocalDateTime.ofInstant(rs.getDate("ENTRADA").toInstant(), ZoneId.of("UTC"));
                    LocalDateTime s = LocalDateTime.ofInstant(rs.getDate("SAIDA").toInstant(), ZoneId.of("UTC"));
                    retorno.add(new ProdutoEstocado(rs.getLong("ID"), p, d, e, s, rs.getString("NR_SERIE")));
                }

            } else {
                System.out.println("Não temos " + clazz + " cadastrado no banco de dados");
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar os Produtos Estocados: " + e.getMessage());
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
    public ProdutoEstocado persist(ProdutoEstocado pe) {

        var clazz = "PRODUTO_ESTOCADO";

        String sql = "INSERT INTO " + clazz + " (ID_PRODUTO, ID_DEPOSITO, ENTRADA, SAIDA, NR_SERIE) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println(sql);

        try {

            ps = open().prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setLong(1, pe.getProduto().getId());
            ps.setLong(2, pe.getDeposito().getId());
            ps.setTimestamp(3, Objects.nonNull(pe.getEntrada()) ? Timestamp.from(pe.getEntrada().toInstant(ZoneOffset.UTC)) : null);
            ps.setTimestamp(4, Objects.nonNull(pe.getSaida()) ? Timestamp.from(pe.getSaida().toInstant(ZoneOffset.UTC)) : null);
            ps.setString(5, pe.getNumeroDeSerie().toUpperCase());

            ps.execute();
            rs = ps.getGeneratedKeys();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    pe.setId(rs.getLong(1));
                }
                return pe;
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
    public ProdutoEstocado update(ProdutoEstocado produtoEstocado) {
        return null;
    }

    @Override
    public void delete(ProdutoEstocado produtoEstocado) {

    }
}

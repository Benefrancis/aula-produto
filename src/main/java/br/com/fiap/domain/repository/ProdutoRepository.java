package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.repository.abstracao.JDBCRepository;
import br.com.fiap.domain.repository.abstracao.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProdutoRepository extends JDBCRepository implements Repository<Produto, Long> {

    public ProdutoRepository() {
        super();
    }

    @Override
    public Collection<Produto> findAll() {

        var clazz = Produto.class.getSimpleName().toUpperCase();

        List<Produto> retorno = new ArrayList<>();

        String sql = "SELECT * from " + clazz;

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement( sql );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    retorno.add( new Produto( rs.getLong( "ID" ), rs.getString( "NOME" ), rs.getString( "DESCRICAO" ), rs.getBigDecimal( "VALOR" ) ) );
                }

            } else {
                System.out.println( "Não temos " + clazz + " cadastrados no banco de dados" );
            }
            return retorno;

        } catch (SQLException e) {
            System.out.println( "Não foi possível consultar o " + clazz + ": " + e.getMessage() );
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println( "Erro ao tentar fechar o Statment ou o ResultSet de " + clazz );
            }
            if (this.connection != null)
                this.closeConnection();
        }

        return retorno;
    }

    @Override
    public Produto findById(Long id) {

        var clazz = Produto.class.getSimpleName().toUpperCase();

        String sql = "SELECT * from " + clazz + " where id  =?";

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement( sql );
            ps.setLong( 1, id );

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    return new Produto( rs.getLong( "ID" ), rs.getString( "NOME" ), rs.getString( "DESCRICAO" ), rs.getBigDecimal( "VALOR" ) );
                }

            } else {
                System.out.println( clazz + " nao encontrado" );
            }

            return null;

        } catch (SQLException e) {
            System.out.println( "Não foi possível consultar o " + clazz + ": " + e.getMessage() );
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println( "Erro ao tentar fechar o Statment ou o ResultSet de " + clazz );
            }

        }

        return null;
    }

    @Override
    public Collection<Produto> findByName(String texto) {
        List<Produto> retorno = new ArrayList<>();
        if (texto.equals( "" )) return retorno;

        var clazz = Produto.class.getSimpleName().toUpperCase();


        String sql = "SELECT * from " + clazz + " where nome  =?";

        System.out.println( sql );

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement( sql );
            ps.setString( 1, texto.toUpperCase() );

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    retorno.add( new Produto( rs.getLong( "ID" ), rs.getString( "NOME" ), rs.getString( "DESCRICAO" ), rs.getBigDecimal( "VALOR" ) ) );
                }

            } else {
                System.out.println( clazz + " nao encontrado" );
            }

            return retorno;

        } catch (SQLException e) {
            System.out.println( "Não foi possível consultar o " + clazz + ": " + e.getMessage() );
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println( "Erro ao tentar fechar o Statment ou o ResultSet de " + clazz );
            }

        }

        return retorno;
    }

    @Override
    public Produto persist(Produto p) {

        var clazz = Produto.class.getSimpleName().toUpperCase();

        String sql = "INSERT INTO " + clazz + " (NOME, DESCRICAO, VALOR) VALUES (?,?,?)";

        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println( sql );

        try {
            ps = getConnection().prepareStatement( sql, ps.RETURN_GENERATED_KEYS );

            ps.setString( 1, p.getNome().toUpperCase() );
            ps.setString( 2, p.getDescricao() );
            ps.setBigDecimal( 3, p.getValor() );

            ps.execute();
            rs = ps.getGeneratedKeys();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    p.setId( rs.getLong( 1 ) );
                }
                return p;
            } else {
                System.out.println( clazz + " nao encontrado" );
            }
        } catch (SQLException e) {
            System.out.println( "Erro ao salvar " + clazz + " no banco de dados: " + e.getMessage() );
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println( "Erro ao tentar fechar o Statement de " + clazz );
            }
        }

        return null;
    }


    @Override
    public Produto update(Produto produto) {
        return null;
    }

    @Override
    public void delete(Produto produto) {

    }
}

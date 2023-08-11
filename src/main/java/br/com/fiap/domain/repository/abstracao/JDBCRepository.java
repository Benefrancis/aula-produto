package br.com.fiap.domain.repository.abstracao;

import br.com.fiap.domain.repository.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe repository
 * <p>
 * Todo reposotorio deveraextender esta classe, pois todo reposit�rio dever�
 * saber se conectar ao banco de dados conforme par�metros do arquivo
 * application.properties
 *
 * @author Francis
 */
public abstract class JDBCRepository {

    public Connection connection;

    public JDBCRepository() {
        super();
        getConnection();
    }


    public Connection getConnection() {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            return this.connection;
        } catch (SQLException e) {
            System.out.println( "Erro nos parametros da conexao com o banco de dados :" + e.getMessage() );
        }
        return null;
    }


    public void closeConnection() {
        try {
            // se a conex�o n�o estiver fechada, feche-a
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            System.out.println( "Erro ao encerrar conexao:" + " \n" + ex.getMessage() );
        }
    }

}
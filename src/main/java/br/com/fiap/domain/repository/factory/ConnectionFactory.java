package br.com.fiap.domain.repository.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe com padrao Singleton e Factory que fornece connection.
 * <p>
 * Caso o usuario tenha setado o parametro
 * datasource.drop-delete-table-and-dados como true, a estrutura do banco de
 * dados sera recriada com valores padrao.
 *
 * @author Francis
 */
public final class ConnectionFactory {

    private static volatile ConnectionFactory instance;

    private String url;
    private String user;
    private String pass;
    private String driver;

    private volatile Connection conexao;

    /**
     * Construtor privado
     *
     * @param url
     * @param user
     * @param pass
     * @param driver
     */
    private ConnectionFactory(String url, String user, String pass, String driver) {
        super();
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    /**
     * �nico meio de se conseguir uma inst�ncia
     *
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static ConnectionFactory getInstance() throws SQLException {

        ConnectionFactory result = instance;

        if (result != null) {
            return result;
        }

        Properties prop = new Properties();
        FileInputStream file = null;
        String debugar = "";
        try {
            file = new FileInputStream( "src/main/resources/application.properties" );
            prop.load( file );

            String url = prop.getProperty( "datasource.url" );
            String user = prop.getProperty( "datasource.username" );
            String pass = prop.getProperty( "datasource.password" );
            String driver = prop.getProperty( "datasource.driver-class-name" );
            debugar = prop.getProperty( "datasource.debugar" );
            file.close();

            if (debugar != null && debugar.equals( "true" )) {
                System.out.println( "\n\n************************  CONNECTION PROPERTIES  **************************\n" );
                System.out.println( "JDBC URL: " + url );
                System.out.println( "USER: " + user );
                System.out.println( "PASSWORD: *******" );
                System.out.println( "DRIVER: " + driver );
            }

            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = new ConnectionFactory( url, user, pass, driver );
                }
                return instance;
            }

        } catch (FileNotFoundException e) {
            System.out.println( "Nao conseguinos encontrar o aquivo de propriedades: " + e.getMessage() );
        } catch (IOException e) {
            System.out.println( "Nao encontramos propriedade com o nome: " + e.getMessage() );
        } finally {
            if (debugar != null && debugar.equals( "true" ))
                System.out.println( "\n**************************************************************************\n" );
        }
        return null;

    }

    /**
     * Fornece a conexao com o banco de dados
     *
     * @return
     * @see DriverManager#getConnection(String)
     * @see Class#forName(String)
     */
    public Connection getConnection() {

        synchronized (Connection.class) {

            try {

                if (this.conexao != null && !this.conexao.isClosed()) {
                    return this.conexao;
                }

                if (this.getDriver() == null || this.getDriver().equals( "" )) {
                    System.out.println( "\nInforme os dados de conexao no arquivo application.properties [ datasource.driver-class-name ]" );
                    throw new RuntimeException( "Informe os dados de conexao no arquivo application.properties [ datasource.driver-class-name ]" );
                }

                if (this.getUrl() == null || this.getUrl().equals( "" )) {
                    System.out.println( "\nInforme os dados de conex�o no arquivo application.properties [ datasource.url ]" );
                    throw new RuntimeException( "Informe os dados de conexao no arquivo application.properties [ datasource.url ]" );
                }

                if (this.getUser() == null || this.getUser().equals( "" )) {
                    System.out.println( "\nInforme os dados de conexao no arquivo application.properties [ datasource.username ]" );
                    throw new RuntimeException( "Informe os dados de conexao no arquivo application.properties [ datasource.username ]" );
                }

                Class.forName( this.getDriver() );

                this.conexao = DriverManager.getConnection( this.getUrl(), this.getUser(), this.getPass() );

            } catch (ClassNotFoundException e) {
                System.out.println( "Nao foi possivel encotrar o driver de conexao: " + e.getMessage() );
                System.exit( 1 );
            } catch (SQLException sqle) {
                System.out.println( "Erro nos parametros da conex�o com o banco de dados :" + sqle.getMessage() );
                System.exit( 1 );
            }
            return this.conexao;
        }

    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }

}
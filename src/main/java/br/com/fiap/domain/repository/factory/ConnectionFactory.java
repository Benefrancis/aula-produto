package br.com.fiap.domain.repository.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

public class ConnectionFactory {


    private static volatile ConnectionFactory instance;

    private volatile Connection conexao;

    private String url;

    private String user;

    private String pass;

    private String driver;

    private ConnectionFactory(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    public static ConnectionFactory build() {

        ConnectionFactory factory = instance;

        if (Objects.nonNull(factory)) {
            return factory;
        }

        Properties prop = new Properties();
        FileInputStream file = null;
        String debugar = "";

        try {
            file = new FileInputStream("src/main/resources/application.properties");
            prop.load(file);

            String url = prop.getProperty("datasource.url");
            String user = prop.getProperty("datasource.username");
            String pass = prop.getProperty("datasource.password");
            String driver = prop.getProperty("datasource.driver-class-name");
            debugar = prop.getProperty("datasource.debugar");

            file.close();

            if(Objects.nonNull(debugar) && debugar.equals("true")){
                System.out.println("*.-".repeat(5) + " CONNECTION PROPERTIES " + "*.-".repeat(5));
                System.out.println("JDBC URL: " + url);
                System.out.println("JDBC USER: " + user);
                System.out.println("JDBC PASSWORD: " + "*******");
                System.out.println("JDBC DRIVER: " + driver);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Não conseguimos encontrar o arquivo de propriedades: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Não conseguimos ler o arquivo de propriedades com o nome: " + e.getMessage());
        } finally {
            if (Objects.nonNull(debugar) && debugar.equals("true"))
                System.out.println("*.-".repeat(20));
        }

        return null;

    }


}

package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;

    private String username = "root";
    private String password = "ifsp";
    private int port = 3306;

    private Connection connection;
    private String url = "jdbc:mysql://localhost:" + port + "/escola";

    private DatabaseSingleton() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver do banco de dados: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseSingleton();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseSingleton();
        }

        return instance;
    }

    // SELECTs e é retornado um ResultSet
    public ResultSet executeSelect( String q ) throws Exception {

        ResultSet rs = null;
        if (!q.isEmpty()) {
            Statement stmt;
            stmt = this.connection.createStatement();
            rs = stmt.executeQuery(q);
        }

        return rs;
    }

    // DML = Data Manipulation Language (INSERTs, UPDATEs e DELETEs)
    public int executeDML( String q ) throws Exception {

        if (!q.isEmpty()) {

            Statement stmt = this.connection.createStatement();
            return stmt.executeUpdate(q);

        }

        return 0;
    }

}

/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alisson Jaques
 */
public class MySqlConnection {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost";
    private static final String PORT = "3306";
    private static final String USUARIO = "aplicacao";
    private static final String SENHA = "admin";
    private static final String BANCO = "ALMOXARIFADO";
    private static final String TIMEZONE = "useTimezone=true&serverTimezone=UTC";
    
    private Connection conn;
    private static MySqlConnection instance;

    public MySqlConnection() throws ClassNotFoundException, SQLException {
    
        String conexao = URL + ":" + PORT + "/" + BANCO + "?" + TIMEZONE;
        
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(conexao, USUARIO, SENHA);
        
    } 

    public static MySqlConnection getInstance() throws ClassNotFoundException, SQLException {
        
        if(instance == null)
            instance = new MySqlConnection();
        else if(instance.getConn().isClosed())
            instance = new MySqlConnection();
        
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
}

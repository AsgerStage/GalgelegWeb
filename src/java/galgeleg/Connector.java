/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Magnus
 */
public class Connector {
//    private final String HOST     = "127.0.0.1";
//    private final int    PORT     = 3306;
//    private final String DATABASE = "galgescores";
//    private final String USERNAME = "root"; 
//    private final String PASSWORD = "";
    private final String HOST     = "ubuntu4.javabog.dk";
    private final int    PORT     = 3306;
    private final String DATABASE = "galgescores";
    private final String USERNAME = "donfranko"; 
    private final String PASSWORD = "dist";
    private static Connection connection;
    
    public Connector() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
    }
    
    public Connection getConnection(){
    	return connection;
    }
    
    public ResultSet doQuery(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;
    }
    
    public void doUpdate(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }
}

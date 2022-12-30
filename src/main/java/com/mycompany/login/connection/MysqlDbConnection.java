/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login.connection;
 import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Fabian Soto Ferreira
 */
public final class MysqlDbConnection implements Closeable{
    
    private static MysqlDbConnection instance;
    
    private static final String USER = "develop";
    private static final String PASSWORD = "admin";
    private static final String URL = "jdbc:mysql://localhost:3306/db_api?serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    private Connection connection;
    
    private PreparedStatement preparedStatemen;
    private PreparedStatement preparedStatement;
    
    
    
    private MysqlDbConnection() {
        
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MysqlDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        public static MysqlDbConnection getInstance() {
            return instance == null ? new MysqlDbConnection() : instance;
        }
    
    
    public ResultSet executeQuery(final String query) throws SQLException {
    this.preparedStatement = connection.prepareStatement(query);
    return this.preparedStatement.executeQuery();
    }
    
    

    @Override
    public void close() throws IOException {
        try {
            if (!connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}

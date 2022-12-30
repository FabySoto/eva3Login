/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login.user.repository;

import com.mycompany.login.connection.MysqlDbConnection;
import com.mycompany.login.user.model.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fabian Soto Ferreira
 */
public class UserRepository implements IUserRepository{
    
    private final static String QUERY_LOGIN = new StringBuilder()
            .append("SELECT id_user, user_name, password FROM user WHERE user_name = '%s' AND password = '%s'")
            .toString();

    /**
     *
     * @param user
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public UserEntity getByLogin(String user, String password) throws SQLException {
        UserEntity response = new UserEntity();
        try (MysqlDbConnection db = MysqlDbConnection.getInstance()){
            
            ResultSet result = db.executeQuery(String.format(QUERY_LOGIN, user, password));
            while (result.next()) {
                response.setId(result.getInt("id_user"));
                response.setUserName(result.getString("user_name"));
                response.setPassword(result.getString("password"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return response;
    }
    
}

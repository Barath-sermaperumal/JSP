package com.codewithbubblu.dao;

import com.codewithbubblu.db.Database;
import com.codewithbubblu.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class UserDao {
    private static Connection connection;
    public UserDao(){
        connection= Database.getConnection();
    }

    private static final String LOGIN_QUERY="select id,username,password from auth where username=? and password=?;";

    public static User login(String username, String password) {
        User user = null;
        try {
            PreparedStatement ps=connection.prepareStatement(LOGIN_QUERY);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                user=new User();
                user.setId(parseInt(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}

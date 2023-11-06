package com.codewithbubblu.dao;

import com.codewithbubblu.db.Database;
import com.codewithbubblu.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private static final String SELECT_ALL_QUERY = "Select * from todo where userid=? and delete_flag=false";
    private static final String INSERT_TODO_QUERY = "insert into todo (userId,item) values (?,?);";
    private final String SELECT_TODO = "SELECT id, todo, userId FROM todo WHERE id=?";
    private final String DELETE_TODO = "update todo set delete_flag=true where id=?;";
    Connection connection;
    public TodoDao(){
        connection= Database.getConnection();
    }


    public List<Todo> selectAllTodos(int userId){
        List<Todo> todos=new ArrayList<>();
        try {
            PreparedStatement ps=connection.prepareStatement(SELECT_ALL_QUERY);
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Todo todo=new Todo();
                todo.setTodo(rs.getString("item"));
                todo.setId(Integer.parseInt(rs.getString("id")));
                todo.setUserId(String.valueOf(rs.getInt("userId")));
                todos.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }

    public void addTodo(String todo,int userId) throws SQLException {
        PreparedStatement ps=connection.prepareStatement(INSERT_TODO_QUERY);
        ps.setInt(1,userId);
        ps.setString(2,todo);
        ps.executeUpdate();
    }

    public void deleteTodo(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_TODO);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Todo getTodo(int id) {
        Todo todo = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_TODO);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                todo = new Todo();
                todo.setId(Integer.parseInt(rs.getString("id")));
                todo.setTodo(rs.getString("todo"));
                todo.setUserId(String.valueOf(rs.getInt("userId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }
}

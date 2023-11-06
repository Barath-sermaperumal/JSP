package com.codewithbubblu.controller;

import com.codewithbubblu.dao.TodoDao;
import com.codewithbubblu.dao.UserDao;
import com.codewithbubblu.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController extends HttpServlet {
    UserDao userDao;
    TodoDao todoDao;
    public HomeController(){
        userDao=new UserDao();
        todoDao=new TodoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            todoDao.deleteTodo(Integer.parseInt(id));
        }
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        String userId = req.getSession().getAttribute("id").toString();

        String item = req.getParameter("todo");

        if (item != null && item.trim().length() > 0) {
            try {
                todoDao.addTodo(item, Integer.parseInt(userId));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        List<Todo> todos = todoDao.selectAllTodos(Integer.parseInt(userId));
        req.setAttribute("todos", todos);
        dispatcher.forward(req, resp);
    }


}

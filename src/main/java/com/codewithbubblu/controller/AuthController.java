package com.codewithbubblu.controller;

import com.codewithbubblu.dao.UserDao;
import com.codewithbubblu.db.Database;
import com.codewithbubblu.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.codewithbubblu.dao.UserDao.login;

public class AuthController extends HttpServlet {
    public AuthController(){
        Database.getConnection();
        UserDao userDao=new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        User loggedInUser= login(username,password);
        if (loggedInUser != null) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("id", loggedInUser.getId());
            RequestDispatcher rd = req.getRequestDispatcher("home");
            rd.forward(req, resp);
        } else {
            req.setAttribute("error", true);
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        }


    }
}

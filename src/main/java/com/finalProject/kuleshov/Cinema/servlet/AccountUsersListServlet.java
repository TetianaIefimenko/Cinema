package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/account/users_list")
public class AccountUsersListServlet extends HttpServlet {
    UserDao userDao = null;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showUsersList(req, resp);
    }

    private void showUsersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userDao.findAllUsers();
        req.setAttribute("listUsers", allUsers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/users_list.jsp");
        dispatcher.forward(req, resp);
    }
}

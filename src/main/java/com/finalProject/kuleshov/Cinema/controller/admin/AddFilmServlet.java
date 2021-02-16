package com.finalProject.kuleshov.Cinema.controller.admin;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.Cinema.entity.Film;
import com.finalProject.kuleshov.Cinema.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/add_new_film")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddFilmServlet extends HttpServlet {
    FilmDao filmDao = null;
    UserDao userDao = null;
    private final String UPLOAD_DIRECTORY = "images";

    @Override
    public void init() throws ServletException {
        filmDao = new MySQLFilmDao();
        userDao = new MySQLUserDao();
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (!content.trim().startsWith("filename")) {
                continue;
            }
            part.getSubmittedFileName();
            return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");

        if (role.equals(User.ROLE.ADMIN)) {
            showNewForm(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uploadPath = request.getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fileName = "";
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(uploadPath + File.separator + fileName);
        }

        String name = request.getParameter("title");
        String directedBy = request.getParameter("director");
        String description = request.getParameter("description");
        int duration = Integer.parseInt(request.getParameter("duration"));
        Film newFilm = new Film(name, directedBy, description, duration, fileName);

        filmDao.addFilm(newFilm);

        response.sendRedirect(request.getContextPath() + "/films_list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/film_form.jsp");
        dispatcher.forward(request, response);
    }


}

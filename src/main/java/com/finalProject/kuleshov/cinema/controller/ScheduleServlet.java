package com.finalProject.kuleshov.cinema.controller;

import com.finalProject.kuleshov.cinema.dao.SeanceDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.cinema.entity.Seance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    private SeanceDao seanceDao = null;
    @Override
    public void init() throws ServletException {
        seanceDao = new MySQLSeanceDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String day = req.getParameter("day");
        String dayOfWeek = null;
        if (day == null) {
            dayOfWeek = "Monday";
        } else {
            dayOfWeek = day;
        }

        List<Seance> seancesWeek = seanceDao.selectScheduleByDay(dayOfWeek);
        req.setAttribute("seancesWeek", seancesWeek);

        String currentDay = seanceDao.selectCurrentDay();
        req.setAttribute("currentDay", currentDay);

        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }
}

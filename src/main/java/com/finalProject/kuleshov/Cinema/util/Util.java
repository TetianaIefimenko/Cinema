package com.finalProject.kuleshov.Cinema.util;

import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLUserDao;
import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final Logger LOG = Logger.getLogger(Util.class);

    public static void close(Statement statement, ResultSet rs, Connection con) {
        try {
            statement.close();
        } catch (SQLException e) {
            LOG.error("Statement close()" + e.getMessage());
        }
        try {
            rs.close();
        } catch (SQLException e) {
            LOG.error("ResultSet close()" + e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            LOG.error("Connection close() " + e.getMessage());
        }
    }

    public static void close(Statement statement, Connection con) {
        try {
            statement.close();
        } catch (SQLException e) {
            LOG.error("Statement close () " + e.getMessage());
            System.out.println("Statement close() exception: " + e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            LOG.error("Connection close () " + e.getMessage());
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Trouble with rollback: " + e.getMessage());
        }
    }

        public static String getCurrentDate () {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(date);
            return currentDate;
        }

        public static String sortingOption (String sortRequest){
            if (sortRequest == null) {
                return "order by 4 asc, 5 asc ";
            }
            String orderByDateTimeAsc = "order by 4 asc, 5 asc ";
            String orderByMovieTitleAsc = "order by 3 asc ";
            String orderByAvailableSeatsAsc = "order by free_places asc ";
            String orderByDateTimeDesc = "order by 4 desc, 5 desc ";
            String orderByMovieTitleDesc = "order by 3 desc ";
            String orderByAvailableSeatsDesc = "order by free_places desc ";
            String orderByPriceAsc = "order by 6 asc ";
            String orderByPriceDesc = "order by 6 desc ";
            switch (sortRequest) {
                case "dateTimeSortAsc":
                    return orderByDateTimeAsc;
                case "availableSeatsSortAsc":
                    return orderByAvailableSeatsAsc;
                case "movieTitleSortAsc":
                    return orderByMovieTitleAsc;
                case "dateTimeSortDesc":
                    return orderByDateTimeDesc;
                case "availableSeatsSortDesc":
                    return orderByAvailableSeatsDesc;
                case "movieTitleSortDesc":
                    return orderByMovieTitleDesc;
                case "priceSortAsc":
                    return orderByPriceAsc;
                case "priceSortDesc":
                    return orderByPriceDesc;
                default:
                    return "order by 4 asc, 5 asc ";
            }
        }
    }
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaskaran
 */
public class ScheduleEvent extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String evname = request.getParameter("eventname");
        String evid = request.getParameter("eventid");
        String dt = request.getParameter("date");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/fest";
        Connection conn = null;
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(DB_URL, "root", "hello");
            stmt = conn.createStatement();
            String sql;//change here
            int f = (int) (getServletConfig().getServletContext().getAttribute("fest"));
            String created = (String)(getServletConfig().getServletContext().getAttribute("uname"));
            sql = "INSERT into schedule values(" + f + "," + evid + ",'" + evname + "','" + dt + "','" + start + "','" + end +"','"+created+"')";
            stmt.executeUpdate(sql);
            request.getRequestDispatcher("/WEB-INF/HomePage.html").forward(request, response);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

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
import java.sql.ResultSet;
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
public class EventSearch extends HttpServlet {
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
        String id = request.getParameter("eid");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/fest";
        Connection conn = null;
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(DB_URL, "root", "hello");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT eventid FROM events";
            ResultSet rs = stmt.executeQuery(sql);
            //out.println("" + rs.getString("password"));
            if (rs == null) {
                out.println("Invalid. Go back to try again.<br>");
                out.close();
            }
            while (rs.next()) {
                String eid = rs.getString("eventid");
                if (eid.equals(id)) {                    
                    rs.close();
                    stmt.close();
                    conn.close();
                    request.getRequestDispatcher("/WEB-INF/ModifyEvent.html").forward(request, response);
                    break;
                }
            }
            out.println("No such event found. Go back to try again.");
            rs.close();
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

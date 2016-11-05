/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaskaran
 */
public class LoginServlet extends HttpServlet {

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
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        if(request.getParameter("accept").equals("signup")){
            request.getRequestDispatcher("/WEB-INF/Signup.html").forward(request, response);
            out.close();
        }
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/fest";
        Connection conn = null;
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, "root", "hello");

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM members";
            ResultSet rs = stmt.executeQuery(sql);
            //out.println("" + rs.getString("password"));
            if (rs == null) {
                out.println("Invalid. Go back to try again.<br>");
                out.close();
            }

            while (rs.next()) {
                String pw = rs.getString("password");
                String unam = rs.getString("loginid");
                if (pwd.equals(pw) && unam.equals(uname)) {
                    int fid = rs.getInt("festid");
                    String pst = rs.getString("post");
                    rs.close();
                    stmt.close();
                    conn.close();
                    ServletContext application = getServletConfig().getServletContext();
                    application.setAttribute("uname", uname);
                    application.setAttribute("fest", fid);
                    application.setAttribute("post", pst);
                    request.getRequestDispatcher("/WEB-INF/HomePage.html").forward(request, response);
                    break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("Invalid. Go back to try again.<br>");
        out.close();
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

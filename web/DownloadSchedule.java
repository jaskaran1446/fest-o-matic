package com.example.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javafx.scene.input.KeyCode.I;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaskaran
 */
public class DownloadSchedule extends HttpServlet {

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
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/fest";
        Connection conn = null;
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(DB_URL, "root", "hello");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT eventname,eventid,date,start,end FROM schedule";
            ResultSet rs = stmt.executeQuery(sql);
            //out.println("" + rs.getString("password"));
            out.write("EID&nbsp;&nbsp;&nbsp;&nbsp;NAME&nbsp;&nbsp;&nbsp;&nbsp;DATE&nbsp;&nbsp;&nbsp;&nbsp;START&nbsp;&nbsp;&nbsp;&nbsp;END<br>");
            while (rs.next()) {
                String name = rs.getString("eventname");
                String date = rs.getString("date");
                String start = rs.getString("start");
                String end = rs.getString("end");
                int id = rs.getInt("eventid");
                out.write(id + "&nbsp;&nbsp;&nbsp;&nbsp;" + name + "&nbsp;&nbsp;&nbsp;&nbsp;" + date + "&nbsp;&nbsp;&nbsp;&nbsp;" + start + "&nbsp;&nbsp;&nbsp;&nbsp;" + end + "<br>");
            }
            out.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        FileInputStream fileInputStream = new FileInputStream(outputFile.getAbsolutePath() + outputFile.getName());
//
//        int i;
//        while ((i = fileInputStream.read()) != -1) {
//            out.write(i);
//        }
//        fileInputStream.close();
//        out.close();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

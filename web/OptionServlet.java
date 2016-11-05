/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaskaran
 */
public class OptionServlet extends HttpServlet {

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
        String c = request.getParameter("choice");
        if (c.equals("createe")) {
            request.getRequestDispatcher("/WEB-INF/CreateEvent.html").forward(request, response);
        } else if (c.equals("creates")) {
            request.getRequestDispatcher("/WEB-INF/Schedule.html").forward(request, response);
        } else if (c.equals("modifye")) {
            request.getRequestDispatcher("/WEB-INF/EventSearch.html").forward(request, response);
        } else if (c.equals("addm")) {
            String pos = (String) (getServletConfig().getServletContext().getAttribute("post"));
            if (pos.equals("head")) {
                request.getRequestDispatcher("/WEB-INF/AddMember.html").forward(request, response);
            } else {
                out.println("OOPS! Only Fest Head can add members.");
                out.close();
            }
        } else if (c.equals("showe")) {
            request.getRequestDispatcher("/WEB-INF/ShowEvents.html").forward(request, response);
        }else {
            request.getRequestDispatcher("/WEB-INF/ShowSchedule.html").forward(request, response);
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

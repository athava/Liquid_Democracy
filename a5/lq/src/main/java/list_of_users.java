/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2017.lq.db.UserDB;
import gr.csd.uoc.cs359.winter2017.lq.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lanag
 */
@WebServlet(urlPatterns = {"/list_of_users"})
public class list_of_users extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<User> users = UserDB.getUsers();
            User usr;

            out.println("<h3>List of registered users</h3>");
            out.println("<table class=\"table\">");
            out.println("<tr><th>Username</th><th>Email</th><th>Last name</th><th>First Name </th></tr>");

            int i = 0;
            while (i < users.size()) {
                usr = users.get(i);

                out.println("<tr><th>" + usr.getUserName() + "</th><td>" + usr.getEmail() + "</td><td>" + usr.getLastName() + "</td><td>" + usr.getFirstName() + "</td><td><a onclick=\" user_pi_list('" + usr.getUserName() + "'  ,'active')\">initiatives</a></td></tr>"
                );

                HttpSession session = request.getSession();
                ServletContext context = session.getServletContext();
                /*context.getAttributeNames();
                Enumeration<String> connected = context.getAttributeNames();
                int j=0;
                while(connected != null){
                    connected[i]
                }
                 */
                System.out.println(context.getAttribute(session.getAttribute("username").toString()).toString());
                if (context.getAttribute(session.getAttribute("username").toString()).equals(usr.getUserName())) {
                    out.println("<td><span style=\"color: blue;\" class=\"glyphicon glyphicon-user\"></span></td></tr>");
                } else {
                    out.println("<td><span class=\"glyphicon glyphicon-user\"></span></td></tr>");
                }


                i++;
            }

            out.println("</table>");
            response.setStatus(200);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(list_of_users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(list_of_users.class.getName()).log(Level.SEVERE, null, ex);
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

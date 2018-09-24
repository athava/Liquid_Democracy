/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/add_initiative"})
public class add_initiative extends HttpServlet {

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
            Initiative pi = new Initiative();
            HttpSession session3 = request.getSession();
            String action = request.getParameter("action");

            if (action.matches("activate")) {
                pi = InitiativeDB.getInitiative(Integer.parseInt(request.getParameter("initiative_id")));

                // check if expired
                if (pi.getExpires().before(new Date())) {
                    pi.setStatus(2);
                } else {
                    pi.setStatus(1);
                }

                InitiativeDB.updateInitiative(pi);

            } else if (action.matches("update")) {
                pi = InitiativeDB.getInitiative(Integer.parseInt(request.getParameter("initiative_id")));
                pi.setTitle(request.getParameter("title"));
                pi.setCategory(request.getParameter("category"));
                pi.setDescription(request.getParameter("description"));

                Date date;
                date = new Date(Integer.valueOf(request.getParameter("year")) - 1900,
                        Integer.valueOf(request.getParameter("month")) - 1,
                        Integer.valueOf(request.getParameter("day")),
                        Integer.valueOf(request.getParameter("hours")),
                        Integer.valueOf(request.getParameter("minutes")),
                        0);

                pi.setExpires(date);
                pi.setStatus(0);
                InitiativeDB.updateInitiative(pi);

            } else if (action.matches("create")) {
                pi.setCreator(session3.getAttribute("username").toString());
                pi.setTitle(request.getParameter("title"));
                pi.setCategory(request.getParameter("category"));
                pi.setDescription(request.getParameter("description"));
                pi.setStatus(0);

                InitiativeDB.addInitiative(pi);
            }

            RequestDispatcher rd = request.getRequestDispatcher("my_pis");
            rd.forward(request, response);
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
            Logger.getLogger(add_initiative.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_initiative.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.db.VoteDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Vote;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
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
 * @author dona
 */
@WebServlet(urlPatterns = {"/add_vote"})
public class add_vote extends HttpServlet {

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
            new_DBmethods dbpi = new new_DBmethods();
            dbpi.expiration();

            boolean thisVote;
            if (request.getParameter("vote").equalsIgnoreCase("true")) {
                thisVote = true;
            } else {
                thisVote = false;
            }
            int initiative_id = parseInt(request.getParameter("initiative_id"));
            System.out.println("initiativeid = " + initiative_id);

            HttpSession session3 = request.getSession(false);
            List<Vote> user_votes = VoteDB.getVotes(session3.getAttribute("username").toString());

            Vote vote = new Vote();
            vote.setUser(session3.getAttribute("username").toString());
            vote.setInitiativeID(initiative_id);
            vote.setVote(thisVote, true);

            if (InitiativeDB.getInitiative(initiative_id).getStatus() == 1) {
                if (user_votes.size() == 0) {
                    VoteDB.addVote(vote);
                } else {
                    boolean update_happened = false;
                    for (int i = 0; i < user_votes.size(); i++) {
                        if (user_votes.get(i).getInitiativeID() == initiative_id) {
                            user_votes.get(i).setVote(thisVote, true);
                            VoteDB.updateVote(user_votes.get(i));
                            update_happened = true;
                            break;
                        }
                    }
                    if (!update_happened) {
                        VoteDB.addVote(vote);
                    }
                }
            }
            if (request.getParameter("pis_page").equalsIgnoreCase("my_pis")) {
                RequestDispatcher rd = request.getRequestDispatcher("my_pis");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("view_all_polls");
                rd.forward(request, response);
            }
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
            Logger.getLogger(add_vote.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_vote.class.getName()).log(Level.SEVERE, null, ex);
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

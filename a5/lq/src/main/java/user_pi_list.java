/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.db.VoteDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import gr.csd.uoc.cs359.winter2017.lq.model.Vote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lanag
 */
@WebServlet(urlPatterns = {"/user_pi_list"})
public class user_pi_list extends HttpServlet {

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
            String uname = request.getParameter("user");

            List<Initiative> pis = InitiativeDB.getInitiatives(uname);

            Initiative pi;

            String stat = request.getParameter("status");

            new_DBmethods dbpi = new new_DBmethods();
            dbpi.expiration();
            out.println("<a onclick=\" user_pi_list('" + uname + "','active')\">active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" user_pi_list('" + uname + "','expired')\">expired</a>");

            if (stat.matches("expired")) {
                out.println("<h3>" + uname + "\'s expired initiatives</h3>");
                out.println("<table class=\"table\">");
                out.println(" <tr>\n"
                        + "        <th>Title</th>\n"
                        + "        <th>Description</th>\n"
                        + "        <th>Category</th>\n"
                        + "        <th>Results</th>\n"
                        + "    </tr> ");
                List<Vote> votes = VoteDB.getAllVotes();

                for (int i = 0; i < pis.size(); i++) {
                    if (pis.get(i).getStatus() == 2) {
                        int upvotes = 0;
                        int downvotes = 0;
                        for (int j = 0; j < votes.size(); j++) {
                            if (votes.get(j).getInitiativeID() == pis.get(i).getId()) {
                                if (votes.get(j).getVote() == 1) {
                                    upvotes++;
                                } else {
                                    downvotes++;
                                }
                            }
                        }

                        out.println(" <tr>\n"
                                + "        <td onclick=\"pi_page(" + pis.get(i).getId() + ")\">" + pis.get(i).getTitle() + "</td>\n"
                                + "        <td style=\"max-width: 500px;\">" + pis.get(i).getDescription() + "</td>\n"
                                + "        <td>" + pis.get(i).getCategory() + "</td>");
                        //    out.println("<td>upvotes:" + upvotes + " / downvotes:" + downvotes + " </td>\n" + "    </tr>");

                        if (upvotes + downvotes == 0) {
                            out.println("<td><span  style=\"font-size: 130%;\">no votes</span></td></tr>");
                        } else if (upvotes == downvotes) {
                            out.println("<td><span  style=\"font-size: 130%;\">tie</span></td></tr>");
                        } else if (upvotes > downvotes) {
                            int result = upvotes * 100 / (upvotes + downvotes);
                            out.println("<td><span  style=\"color: green; font-size: 130%;\">" + result + "%&nbsp;&nbsp;<span style=\"transform: rotate(180deg);\" class=\"glyphicon glyphicon-thumbs-down\"></span></span></td></tr>");
                        } else {
                            int result = downvotes * 100 / (upvotes + downvotes);
                            out.println("<td><span  style=\"color: red; font-size: 130%;\">" + result + "%&nbsp;&nbsp;<span class=\"glyphicon glyphicon-thumbs-down\"></span></span></td></tr>");
                        }
                    }
                }
                out.println("</table>");

            } else if (stat.matches("active")) {
                out.println("<h3>" + uname + "\'s active initiatives</h3>");
                out.println("<table class=\"table\">");
                out.println(" <tr>\n"
                        + "        <th>Title</th>\n"
                        + "        <th>Description</th>\n"
                        + "        <th>Category</th>\n"
                        + "        <th></th>\n"
                        + "        <th></th>\n"
                        + "    </tr> ");

                List<Vote> votes = VoteDB.getVotes(uname);

                for (int i = 0; i < pis.size(); i++) {
                    if (pis.get(i).getStatus() == 1) {
                        String up_color = "gray";
                        String down_color = "gray";
                        for (int j = 0; j < votes.size(); j++) {
                            if (votes.get(j).getVotedBy() == 1) {
                                if (votes.get(j).getInitiativeID() == pis.get(i).getId() && votes.get(j).getVote() == 0) {
                                    down_color = "red";
                                } else if (votes.get(j).getInitiativeID() == pis.get(i).getId() && votes.get(j).getVote() == 1) {
                                    up_color = "green";
                                }
                            }
                        }
                        out.println(" <tr>\n"
                                + "        <td onclick=\"pi_page(" + pis.get(i).getId() + ")\">" + pis.get(i).getTitle() + "</td>\n"
                                + "        <td style=\"max-width: 500px;\">" + pis.get(i).getDescription() + "</td>\n"
                                + "        <td>" + pis.get(i).getCategory() + "</td> <td>");

                        out.println("<a onclick=\"add_vote('true', " + pis.get(i).getId() + ", 'my_pis')\"><span  style=\"color:" + up_color + "; transform: rotate(180deg);  font-size: 130%;\" class=\"glyphicon glyphicon-thumbs-down\"></span></a>&nbsp;&nbsp;\n"
                                + "<a onclick=\"add_vote('false', " + pis.get(i).getId() + ", 'my_pis')\"><span style=\"color:" + down_color + "; font-size: 130%;\" class=\"glyphicon glyphicon-thumbs-down\"></span></a>");
                        out.println(" </td></tr> ");
                    }
                }
                out.println("</table>");
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
            Logger.getLogger(user_pi_list.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(user_pi_list.class.getName()).log(Level.SEVERE, null, ex);
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

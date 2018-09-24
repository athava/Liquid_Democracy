/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.db.UserDB;
import gr.csd.uoc.cs359.winter2017.lq.db.VoteDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import gr.csd.uoc.cs359.winter2017.lq.model.User;
import gr.csd.uoc.cs359.winter2017.lq.model.Vote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/my_pis"})
public class my_pis extends HttpServlet {

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
            User user2;
            HttpSession session3 = request.getSession(false);
            user2 = UserDB.getUser(session3.getAttribute("username").toString());

            List<Initiative> pis = InitiativeDB.getInitiatives(user2.getUserName());

            Initiative pi;

            String param = request.getParameter("action");

            new_DBmethods dbpi = new new_DBmethods();
            dbpi.expiration();

            if (param.matches("expired")) {
                out.println("<a onclick=\" fetch_my_pi('inactive')\">non active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" fetch_my_pi('active')\">active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" fetch_my_pi('expired')\">expired</a>");
                out.println("<h3>My expired initiatives</h3>");
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
                                + "        <td>" + pis.get(i).getTitle() + "</td>\n"
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

            } else if (param.matches("active")) {
                out.println("<a onclick=\" fetch_my_pi('inactive')\">non active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" fetch_my_pi('active')\">active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" fetch_my_pi('expired')\">expired</a>");
                out.println("<h3>My active initiatives</h3>");
                out.println("<table class=\"table\">");
                out.println(" <tr>\n"
                        + "        <th>Title</th>\n"
                        + "        <th>Description</th>\n"
                        + "        <th>Category</th>\n"
                        + "        <th></th>\n"
                        + "        <th></th>\n"
                        + "    </tr> ");

                List<Vote> votes = VoteDB.getVotes(session3.getAttribute("username").toString());

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
                                + "        <td>" + pis.get(i).getTitle() + "</td>\n"
                                + "        <td style=\"max-width: 500px;\">" + pis.get(i).getDescription() + "</td>\n"
                                + "        <td>" + pis.get(i).getCategory() + "</td> <td>");

                        out.println("<a onclick=\"add_vote('true', " + pis.get(i).getId() + ", 'my_pis')\"><span  style=\"color:" + up_color + "; transform: rotate(180deg);  font-size: 130%;\" class=\"glyphicon glyphicon-thumbs-down\"></span></a>&nbsp;&nbsp;\n"
                                + "<a onclick=\"add_vote('false', " + pis.get(i).getId() + ", 'my_pis')\"><span style=\"color:" + down_color + "; font-size: 130%;\" class=\"glyphicon glyphicon-thumbs-down\"></span></a>");
                        out.println(" </td></tr> ");
                    }
                }
                out.println("</table>");
            } else {
                out.println("<a onclick=\" fetch_my_pi('inactive')\">non active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" fetch_my_pi('active')\">active</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a onclick=\" fetch_my_pi('expired')\">expired</a>");
                out.println("<h3>My non-active initiatives</h3>");
                out.println("<table class=\"table\">");
                out.println("<tr><th>Title</th><th>Description</th><th>Category</th><th>Expiration</th><th></th><th></th></tr>");

                String exp = "";
                int i = 0;
                while (i < pis.size()) {
                    pi = pis.get(i);
                    if (pi.getStatus() == 0) {

                        out.println("<tr><td>" + pi.getTitle() + "</td>"
                                + "<td>" + pi.getDescription() + "</td>"
                                + "<td>" + pi.getCategory() + "</td>");

                        exp = "" + pi.getExpires();
                        if (pi.getExpires() == null) {
                            exp = "not set";
                            out.println("<td>" + exp + "</td>"
                                    + "<td><a onclick=\"call_update_pi(" + pi.getId() + ")\"><span class=\"glyphicon glyphicon-edit\"></span>Edit</a></td></tr>");

                        } else {
                            if (pi.getExpires().before(new Date())) {
                                out.println("<td style=\" color:red \">" + exp + "</td>"
                                        + "<td><a onclick=\"call_update_pi(" + pi.getId() + ")\"><span class=\"glyphicon glyphicon-edit\"></span>Edit</a></td></tr>");
                            } else {
                                out.println("<td style=\" color:green \">" + exp + "</td>"
                                        + "<td><a onclick=\"call_update_pi(" + pi.getId() + ")\"><span class=\"glyphicon glyphicon-edit\"></span>Edit</a></td>"
                                        + "<td><a onclick=\"activate_pi(" + pi.getId() + ")\"><span class=\"glyphicon glyphicon-ok\"></span>Activate</a></td></tr>");
                            }

                        }

                    }

                    i++;
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
            Logger.getLogger(my_pis.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(my_pis.class.getName()).log(Level.SEVERE, null, ex);
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

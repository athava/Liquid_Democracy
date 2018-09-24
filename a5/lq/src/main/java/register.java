/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gr.csd.uoc.cs359.winter2017.lq.db.UserDB;
import gr.csd.uoc.cs359.winter2017.lq.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lanag
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            User user2 = new User();
            // didn't use constructor to set attributes, in order not to mess with the order
            user2.setUserName(request.getParameter("username"));
            user2.setEmail(request.getParameter("email"));
            user2.setPassword(request.getParameter("password"));
            user2.setFirstName(request.getParameter("fname"));
            user2.setLastName(request.getParameter("lname"));
            user2.setBirthDate(request.getParameter("birthday"));
            user2.setOccupation(request.getParameter("occupation"));
            user2.setCountry(request.getParameter("country"));
            user2.setTown(request.getParameter("town"));

            String input = request.getParameter("address").replace('<', '.');
            input = input.replace('>', '.');

            user2.setAddress(input);
            user2.setGender(request.getParameter("gender"));

            input = request.getParameter("info").replace('<', '.');
            input = input.replace('>', '.');

            user2.setInfo(input);

            input = request.getParameter("interests").replace('<', '.');
            input = input.replace('>', '.');

            user2.setInterests(input);

            String result = user2.validation();

            if (result.matches("") && UserDB.checkValidUserName(user2.getUserName())) {
                UserDB.addUser(user2);

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(3600);
                session.setAttribute("username", user2.getUserName());

                Cookie cook = new Cookie("persistent_uname", user2.getUserName());
                cook.setMaxAge(1800);
                response.addCookie(cook);

                json lala = new json();
                out.print(lala.jsonResponse("register", true, "ok", user2.user2json()));
                response.setStatus(220);
            } else {
                result = "incorrect_fields ".concat(result);
                out.println(result);
                response.setStatus(400);
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
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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

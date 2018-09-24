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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dona
 */
@WebServlet(urlPatterns = {"/update_user"})
public class update_user extends HttpServlet {

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
        
         
         String password = request.getParameter("password");
         String fname = request.getParameter("firstname");
         String lmname = request.getParameter("lastname");
         String birth = request.getParameter("birthday");
         String gender = request.getParameter("gender");
         String country = request.getParameter("country");
         String city = request.getParameter("city");
         String address = request.getParameter("address");
         String occupation = request.getParameter("occupation");
         String interests = request.getParameter("interests");
         String info = request.getParameter("gen_info");
        
         User user =UserDB.getUser(request.getSession().getAttribute("username").toString());
         
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           user.setPassword(password);
           user.setFirstName(fname);
           user.setLastName(lmname);
           user.setBirthDate(birth);
           user.setGender(gender);
           user.setCountry(country);
           user.setCity(city);
           user.setAddress(address);
           user.setOccupation(occupation);
           user.setInterests(interests);
           user.setInfo(info);
           
           UserDB.updateUser(user);
           
           System.out.println(">>>>>>>>>>>>>>>>>>>>>"+UserDB.getUser(request.getSession().getAttribute("username").toString())+"<<<<<<<<<<<<<<<<<<<<<<");
           request.getRequestDispatcher("/loged_in.jsp").forward(request, response);
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
            Logger.getLogger(update_user.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_user.class.getName()).log(Level.SEVERE, null, ex);
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

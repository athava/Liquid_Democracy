/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2017.lq.db.UserDB;
import gr.csd.uoc.cs359.winter2017.lq.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dona
 */
@WebServlet(urlPatterns = {"/validator"})
public class validator extends HttpServlet {

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
            throws ServletException, IOException {

        User newuser = new User(request.getParameter("username"), request.getParameter("email"), request.getParameter("password"),
                request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("birthday"),
                request.getParameter("gender"), request.getParameter("country"), request.getParameter("city"), request.getParameter("address"),
                request.getParameter("occupation"), request.getParameter("interests"), request.getParameter("gen_info"));

        try (PrintWriter out = response.getWriter()) {
            try {
                newuser.checkFields();
                try {
                    String pattern_errors = pattern_validator(newuser);
                    pattern_exeption(pattern_errors);
                    if (UserDB.checkValidUserName(newuser.getUserName()) && UserDB.checkValidUserName(newuser.getEmail())) {
                        try {
                            UserDB.addUser(newuser);
                            System.out.println(newuser.toString());
                            request.getSession().setAttribute("username",newuser.getUserName());
                            request.getRequestDispatcher("/loged_in.jsp").forward(request, response);

                        } catch (Exception ex) {
                            response.setStatus(445);
                        }
                    }

                } catch (Exception ex) { //exception gia missmatch
                    response.setStatus(445);
                    out.append("<h1>Bad Request</h1> pattern missmatch at: " + pattern_validator(newuser));
                }
            } catch (Exception ex) {  //exception gia reguired fields
                response.setStatus(445);
                out.append("<h1>Bad Request</h1> required Fields: " + newuser.required_fields());
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
        processRequest(request, response);

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
        processRequest(request, response);
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

    public String pattern_validator(User user) {
        String errors = "";
        if (!user.getUserName().matches("^([A-Za-z]{8,})$")) {
            errors = errors + "username,  ";
        }
        if (!user.getEmail().matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")) {
            errors = errors + "email, ";
        }
        if (!user.getPassword().matches("(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,10}$")) {
            errors = errors + "password, ";
        }
        if (!user.getFirstName().matches("[A-Za-z]{1,20}")) {
            errors = errors + "firstname, ";
        }
        if (!user.getLastName().matches("[A-Za-z]{4,20}")) {
            errors = errors + "last name, ";
        }
        if (!user.getBirthDate().matches("^(?:(?:(?:0[1-9]|1\\d|2[0-8])/(?:0[1-9]|1[0-2])|(?:29|30)/(?:0[13-9]|1[0-2])|31/(?:0[13578]|1[02]))/[1-9]\\d{3}|29/02/(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00))$")) {
            errors = errors + "birthday, ";
        }
        if (!user.getCity().matches("[A-Za-z]{2,20}")) {
            errors = errors + "city, ";
        }
        if (!user.getAddress().matches("[A-Za-z0-9 ]{0,100}")) {
            errors = errors + "address, ";
        }
        if (!user.getOccupation().matches("[A-Za-z0-9 ]{2,20}")) {
            errors = errors + "occupation";
        }
        return errors;
    }

    public void pattern_exeption(String errors) throws Exception {

        if (!(errors == null || errors.trim().isEmpty())) {
            throw new Exception("pattern missmatch!");
        }
    }
    /*
    private String return_page(User user){
        String str="<div class=\"form-horizontal\" >\n" +
                            "<div class=\"tablee form-group\">\n" +
                                "<p>User is successfully created</p>\n"+
                                "<table >\n" +
                                "   <tr>\n" +
                                "	<td>\n" +
                                "           <p>Username:&nbsp;&nbsp;"+ user.getUserName() +"</p>\n" +
                                "	</td>  \n" +
                                "   </tr>\n" +
                                "\n" +
                                "   <tr>\n" +
                                "	<td>\n" +
                                "           <p >Email:&nbsp;&nbsp;"+ user.getEmail() +"</p>\n" +
                                "       </td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>Password:&nbsp;&nbsp;"+ user.getPassword() +"</p>\n" +
                                "	</td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "   <tr>\n" +
                                "	<td>\n" +
                                "           <p>First name:&nbsp;&nbsp;"+ user.getFirstName() +"</p>\n" +
                                "       </td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>Last name:&nbsp;&nbsp;"+ user.getLastName() +"</p>\n" +
                                "	</td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>Birthday:&nbsp;&nbsp;"+ user.getBirthDate() +"</p>\n" +
                                "	</td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>Gender:&nbsp;&nbsp;"+ user.getGender() +"</p>\n" +
                                "	</td>  \n" +
                                "   </tr>\n" +
                                "\n" +
                                "   <tr>\n" +
                                "	<td>\n" +
                                "           <p>Country:&nbsp;&nbsp;"+ user.getCountry() +"</p>\n" +
                                "	</td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>City:&nbsp;&nbsp;"+ user.getCity() +"</p>\n" +
                                "	</td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>Address:&nbsp;&nbsp;"+ user.getAddress() +"</p>\n" +
                                "	</td>  \n" +
                                "    </tr>\n" +
                                "\n" +
                                "    <tr>\n" +
                                "	<td>\n" +
                                "           <p>Occupation:&nbsp;&nbsp;"+ user.getOccupation() +"</p>\n" +
                                "	</td>  \n" +
                                "   </tr>\n" +
                                "\n" +
                                "   <tr>\n" +
                                "	<td>\n" +
                                "           <p >Interests:&nbsp;&nbsp;"+ user.getInterests() +"</p>\n" +
                                "	</td>  \n" +
                                "   </tr>\n" +
                                "\n" +
                                "   <tr>\n" +
                                "	<td>\n" +
                                "           <p>General Information:&nbsp;&nbsp;"+ user.getInfo() +"</p>\n" +
                                "       </td>  \n" +
                                "   </tr>\n" +
                                "</table>\n" +
                            "</div>\n" +
                        "</div>\n" ;
        return str;
    }*/
}

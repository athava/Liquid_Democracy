<%-- 
    Document   : update_pi
    Created on : Dec 9, 2017, 6:08:25 PM
    Author     : lanag
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.Initiative"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h3>New policy initiative</h3>

<%
    //  User user2;
    HttpSession session3 = request.getSession();
    //  user2= UserDB.getUser(session3.getAttribute("username").toString());
    int pi_id= Integer.parseInt(request.getParameter("initiative_id")); 
    Initiative pi= InitiativeDB.getInitiative(pi_id);
   /* 
    Date date= pi.getExpires();
    System.out.println("exp date: (date) "+ date );
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(date);
    
    String expdate= calendar.get(Calendar.YEAR) +"-"
                    +calendar.get(Calendar.MONTH)+"-"
                    +calendar.get(Calendar.DAY_OF_MONTH)+"T"
                    +calendar.get(Calendar.HOUR_OF_DAY )+":"
                    +calendar.get(Calendar.MINUTE ); 

 System.out.println("expiration date: "+expdate);
*/
%>

<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Title: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="text" id="title" pattern=".{1,100}" title="title of policy initiative" value="<%= pi.getTitle() %>">
    </div>
    <div class="col-md-12 col-xs-12 star" id="title_msg"></div>
</div>
<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Description: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="text" id="description" pattern=".{1,300}" title="describe the policy initiative to be voted" value="<%= pi.getDescription() %>">
    </div>
    <div class="col-md-12 col-xs-12 star" id="description_msg"></div>
</div>
<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Category: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="text" id="category" pattern=".{1,50}" title="category of policy initiative" value="<%= pi.getCategory() %>">
    </div>
    <div class="col-md-12 col-xs-12 star" id="category"></div>
</div>
<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Due date: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="datetime-local" id="date" title="expiration of policy initiative">
    </div>
    <div class="col-md-12 col-xs-12 star" id="category"></div>
</div>

<button class="butt btn" id="create_pi" onclick="fetch_my_pi('inactive')"> Cancel </button>
<button class="butt btn btn-info" id="create_pi" onclick="update_pi( <%= pi.getId() %> )"> Update </button>

<%-- 
    Document   : edit_pinfo
    Created on : Dec 1, 2017, 5:18:45 PM
    Author     : lanag
--%>

<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.UserDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="col-md-4 col col-xs-12">Personal Information</h3>
<div class="col-md-6 col col-xs-12"> 
    <button class="butt btn btn-success" id="editbtn" onclick="update_info()">Update info</button>
</div>    
<%
    User user2;
    HttpSession session2 = request.getSession();
    user2 = UserDB.getUser(session2.getAttribute("username").toString());
%>

<table class="table">
    <tr><th>Username</th><td id="username"><%= user2.getUserName()%></td></tr>
    <tr><th>Email</th><td><input type="text" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="email" value="<%= user2.getEmail()%>"></td></tr>
    <tr><th>Password</th><td id="pw"><%= user2.getPassword()%></td></tr>
    <tr><th>First name</th><td><input type="text" id="fname" pattern="[A-Za-z]{1,20}" title="first name" value="<%= user2.getFirstName()%>"></td></tr>
    <tr><th>Last name</th><td><input type="text" id="lname" pattern="[A-Za-z]{4,20}" title="last name" value="<%= user2.getLastName()%>" > </td></tr>
    <tr><th>Birthday</th><td><input type="text" id="bd" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])\/(0[1-9]|1[012])\/[0-9]{4}" value="<%= user2.getBirthDate()%>"></td></tr>
    <tr><th>Occupation</th><td><input type="text" id="occupation" pattern=".{2,20}" value="<%= user2.getOccupation()%>" ></td></tr>
    <tr><th>Gender</th><td id="gender"><%= user2.getGender()%> </td></tr>
    <tr><th>Country</th><td><input type="text" id="country" pattern=".{0,100}" value="<%= user2.getCountry()%>" ></td></tr>
    <tr><th>City</th><td><input type="text" id="city" pattern="[A-Za-z]{2,20}" value="<%= user2.getTown()%>" ></td></tr>
    <tr><th>Address</th><td><input type="text" id="address" pattern=".{0,100}" value="<%= user2.getAddress()%>" ></td></tr>
    <tr><th>Interests</th><td><input type="text" id="interests" pattern=".{0,100}" value="<%= user2.getInterests()%>"></td></tr>
    <tr><th>Info</th><td><input type="text" id="ginfo" pattern=".{0,500}" value="<%= user2.getInfo()%>"></td></tr>
</table>

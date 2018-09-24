<%-- 
    Document   : pinfo
    Created on : Nov 30, 2017, 11:59:09 PM
    Author     : lanag
--%>

<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.UserDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.User"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="col-md-4 col col-xs-12">Personal Information</h3>
<div class="col-md-6 col col-xs-12"> 
    <button class="butt btn btn-info" id="editbtn" onclick="edit_info()">Edit info</button>
</div>    
<%
    User user2;
    HttpSession session2 = request.getSession();
    user2 = UserDB.getUser(session2.getAttribute("username").toString());
%>

<table class="table">
    <tr><th>Username</th><td> <%= user2.getUserName()%> </td></tr>
    <tr><th>Email</th><td> <%= user2.getEmail()%> </td></tr>
    <tr><th>Password</th><td> <%= user2.getPassword()%> </td></tr>
    <tr><th>First name</th><td> <%= user2.getFirstName()%> </td></tr>
    <tr><th>Last name</th><td> <%= user2.getLastName()%> </td></tr>
    <tr><th>Birthday</th><td> <%= user2.getBirthDate()%> </td></tr>
    <tr><th>Occupation</th><td> <%= user2.getOccupation()%> </td></tr>
    <tr><th>Gender</th><td> <%= user2.getGender()%> </td></tr>
    <tr><th>Country</th><td> <%= user2.getCountry()%> </td></tr>
    <tr><th>City</th><td> <%= user2.getTown()%> </td></tr>
    <tr><th>Address</th><td> <%= user2.getAddress()%> </td></tr>
    <tr><th>Interests</th><td> <%= user2.getInterests()%> </td></tr>
    <tr><th>Info</th><td> <%= user2.getInfo()%> </td></tr>
</table>

<%-- 
    Document   : reg_ok_site
    Created on : Dec 1, 2017, 10:49:30 AM
    Author     : lanag
--%>

<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.UserDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 style="color:green;">Registration successful</h3>    
<h3>Personal Information</h3>
<%
    User user2;
    HttpSession session3 = request.getSession();
    user2 = UserDB.getUser(session3.getAttribute("username").toString());
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

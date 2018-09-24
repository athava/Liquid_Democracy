<%-- 
    Document   : add_initiative
    Created on : Dec 6, 2017, 6:05:27 PM
    Author     : lanag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>New policy initiative</h3>

<%
    //  User user2;
    HttpSession session3 = request.getSession();
    //  user2= UserDB.getUser(session3.getAttribute("username").toString());
%>

<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Title: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="text" id="title" pattern=".{1,100}" title="title of policy initiative">
    </div>
    <div class="col-md-12 col-xs-12 star" id="title_msg"></div>
</div>
<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Description: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="text" id="description" pattern=".{1,300}" title="describe the policy initiative to be voted">
    </div>
    <div class="col-md-12 col-xs-12 star" id="description_msg"></div>
</div>
<div class="col-md-12 col-xs-12" id="myfield">
    <div class="col-md-1 col-xs-12">
        Category: 
    </div>
    <div class="col-md-11 col-xs-12">
        <input type="text" id="category" pattern=".{1,50}" title="category of policy initiative">
    </div>
    <div class="col-md-12 col-xs-12 star" id="category"></div>
</div>

<button class="butt btn " id="create_pi" onclick="fetch_my_pi('inactive')"> Cancel </button>
<button class="butt btn btn-success" id="create_pi" onclick="create_pi()"> Create </button>




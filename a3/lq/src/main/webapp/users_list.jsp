<%-- 
    Document   : users_list
    Created on : Dec 1, 2017, 2:25:32 PM
    Author     : dona
--%>


<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="gr.csd.uoc.cs359.winter2017.lq.db.UserDB,gr.csd.uoc.cs359.winter2017.lq.model.User"%>
<div class="container myheader">

    <div class="page-header"><h1>Liquid Democracy</h1>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li><a onclick="already_loged_in()"><div>My Profile</div></a></li>
                    <li><a onclick="edit_user_ajax()"><div>Edit Profile</div></a></li>
                    <li><a><div id="selected_nav">View Users</div></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a onclick="log_out_ajax()"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                </ul>
            </div>
        </nav>
    </div>

</div>

<div class="container">
    <% List<User> users = UserDB.getUsers(); %>

    <div class="form-horizontal" >  
        <div class="tablee form-group">  
            <table > 
                <tr style="border-style:double;"class="all_users_view_table" >  
                    <td>  
                        <p>USERNAME</p>  
                    </td>
                    <td>  
                        <p>FIRST NAME</p>  
                    </td>
                    <td>  
                        <p>LAST NAME</p>  
                    </td>
                </tr>
                <% for(int i=0; i<users.size(); i++){ ; %>
                <tr class="all_users_view_table" >  
                    <td>  
                        <p><%= users.get(i).getUserName() %></p>  
                    </td>
                    <td>  
                        <p><%= users.get(i).getFirstName() %></p>  
                    </td>
                    <td>  
                        <p><%= users.get(i).getLastName() %></p>  
                    </td>
                </tr>  
                <% } %>
            </table>
            <p id="welcome_msg" ></p>
        </div>
    </div>
</div>


<%-- 
    Document   : loged_in
    Created on : Nov 30, 2017, 4:32:57 PM
    Author     : dona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="gr.csd.uoc.cs359.winter2017.lq.db.UserDB,gr.csd.uoc.cs359.winter2017.lq.model.User"%>

        <div class="container myheader">
           
            <div class="page-header"><h1>Liquid Democracy</h1>
                    <nav class="navbar navbar-inverse">
                      <div class="container-fluid">
                        <ul class="nav navbar-nav">
                          <li><a ><div id="selected_nav">My Profile</div></a></li>
                          <li><a onclick="edit_user_ajax()"><div>Edit Profile</div></a></li>
                          <li><a onclick="view_all_users_ajax()"><div>View Users</div></a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                          <li><a onclick="log_out_ajax()"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                        </ul>
                      </div>
                    </nav>
            </div>
		
        </div>
        
        <div class="container">
            <% User user =UserDB.getUser(request.getSession().getAttribute("username").toString());%>
            
            <div class="form-horizontal" >  
                <div class="tablee form-group">  
                    
                    <table >  
                       <tr>  
                            <td>  
                               <p>Username:&nbsp;&nbsp; <%= user.getUserName()%></p>  
                            </td>    
                       </tr>  

                       <tr>  
                            <td>  
                               <p >Email:&nbsp;&nbsp;<%= user.getEmail() %></p>  
                           </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>Password:&nbsp;&nbsp;<%= user.getPassword() %></p>  
                            </td>    
                        </tr>  

                       <tr>  
                            <td>  
                              <p>First name:&nbsp;&nbsp;<%= user.getFirstName() %></p>  
                           </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>Last name:&nbsp;&nbsp;<%= user.getLastName() %></p>  
                            </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>Birthday:&nbsp;&nbsp;<%= user.getBirthDate() %></p>  
                            </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>Gender:&nbsp;&nbsp;<%= user.getGender() %></p>  
                            </td>    
                       </tr>  

                       <tr>  
                            <td>  
                               <p>Country:&nbsp;&nbsp;<%= user.getCountry() %></p>  
                            </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>City:&nbsp;&nbsp;<%= user.getCity() %></p>  
                            </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>Address:&nbsp;&nbsp;<%= user.getAddress() %></p>  
                            </td>    
                        </tr>  

                        <tr>  
                            <td>  
                               <p>Occupation:&nbsp;&nbsp;<%= user.getOccupation() %></p>  
                            </td>    
                       </tr>  

                       <tr>  
                            <td>  
                               <p >Interests:&nbsp;&nbsp;<%= user.getInterests() %></p>  
                            </td>    
                       </tr>  

                       <tr>  
                            <td>  
                              <p>General Information:&nbsp;&nbsp;<%= user.getInfo() %></p>  
                           </td>    
                       </tr>
                    </table>
                           <p id="welcome_msg" ></p>
                </div>
            </div>
        </div>


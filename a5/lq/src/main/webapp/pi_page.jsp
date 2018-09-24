<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.VoteDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.Vote"%>
<%@page import="java.util.List"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.Initiative"%>
<%-- 
    Document   : pi_page
    Created on : Dec 16, 2017, 12:46:30 PM
    Author     : lanag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%    
    Initiative pi;
    pi = InitiativeDB.getInitiative(Integer.parseInt(request.getParameter("initiative_id")));
%>

<table class="table">
    <tr><th>Title</th>
        <td> <%= pi.getTitle() %> </td></tr>

    <tr><th>Description</th>
        <td> <%= pi.getDescription() %> </td></tr>

    <tr><th>Category</th>
        <td> <%= pi.getCategory() %> </td></tr>

    <tr><th>Creator</th>
        <td> <%= pi.getCreator() %> </td></tr>

<% 
    if(pi.getStatus()==0){
%>
        <tr><th>Expiration date</th>
            <td> <%= pi.getExpiresAsString() %> </td></tr>

        <tr><th>Status</th>
            <td> non-active </td></tr>
<% 
    }else if(pi.getStatus()==1){
%>
    <tr><th>Expiration date</th>
        <td> <%= pi.getExpiresAsString() %> </td></tr>
    
    <tr><th>Status</th>
        <td> active </td></tr>
    
    <tr><th>Vote</th>
        <td>
<% 
        List<Vote> votes = VoteDB.getVotes(session.getAttribute("username").toString());
        String up_color = "gray";
        String down_color = "gray";
        for (int j = 0; j < votes.size(); j++) {
            if (votes.get(j).getVotedBy() == 1) {
                if (votes.get(j).getInitiativeID() == pi.getId() && votes.get(j).getVote() == 0) {
                    down_color = "red";
                } else if (votes.get(j).getInitiativeID() == pi.getId() && votes.get(j).getVote() == 1) {
                    up_color = "green";
                }
            }
        }

        out.println("<a onclick=\"add_vote('true', " + pi.getId() + ", 'pi_page.jsp')\"><span  style=\"color:" + up_color + "; transform: rotate(180deg);  font-size: 130%;\" class=\"glyphicon glyphicon-thumbs-down\"></span></a>&nbsp;&nbsp;\n"
                + "<a onclick=\"add_vote('false', " + pi.getId() + ", 'pi_page.jsp')\"><span style=\"color:" + down_color + "; font-size: 130%;\" class=\"glyphicon glyphicon-thumbs-down\"></span></a>");
%>

        </td></tr>

    <tr><th>Rate</th>
        <td>  <input type="number" id="rating" name="quantity" min="1" max="5" > <input type="butt btn btn-success" value="submit" onclick="set_rating()">  </td></tr>
    <% 
    }else if(pi.getStatus()==2){
%>
    <tr><th>Expired at</th>
        <td> <%= pi.getExpiresAsString() %> </td></tr>
    
    <tr><th>Status</th>
        <td> expired </td></tr>
    
    <tr><th>Result</th>
        <td>
<% 
        List<Vote> votes = VoteDB.getAllVotes();
        int upvotes = 0;
        int downvotes = 0;
        for (int j = 0; j < votes.size(); j++) {
            if (votes.get(j).getInitiativeID() == pi.getId()) {
                if (votes.get(j).getVote() == 1) {
                    upvotes++;
                } else {
                    downvotes++;
                }
            }
        }
        
        if (upvotes + downvotes == 0) {
            out.println("<span  style=\"font-size: 130%;\">no votes</span>");
        } else if (upvotes == downvotes) {
            out.println("<span  style=\"font-size: 130%;\">tie</span>");
        } else if (upvotes > downvotes) {
            int result = upvotes * 100 / (upvotes + downvotes);
            out.println("<span  style=\"color: green; font-size: 130%;\">" + result + "%&nbsp;&nbsp;<span style=\"transform: rotate(180deg);\" class=\"glyphicon glyphicon-thumbs-down\"></span></span>");
        } else {
            int result = downvotes * 100 / (upvotes + downvotes);
            out.println("<span  style=\"color: red; font-size: 130%;\">" + result + "%&nbsp;&nbsp;<span class=\"glyphicon glyphicon-thumbs-down\"></span></span>");
        }
%>
    </td></tr>
    
<% } %>

</table>
<%--
    Document   : view_all_polls
    Created on : Dec 8, 2017, 11:47:27 PM
    Author     : dona
--%>

<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.VoteDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.Vote"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.Initiative"%>
<%@page import="java.util.List"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.db.UserDB"%>
<%@page import="gr.csd.uoc.cs359.winter2017.lq.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3>All Polls</h3>

<%
    User user2;
    HttpSession session3 = request.getSession(false);
    user2 = UserDB.getUser(session3.getAttribute("username").toString());

    List<Initiative> pis = InitiativeDB.getAllInitiatives();

%>
<table class="table">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Category</th>
        <th></th>
        <th></th>
    </tr>
    <% for (int i = 0; i < pis.size(); i++) {
            //to add: check if pi is expired, if so change status
            if (pis.get(i).getStatus() == 1) {

    %>

    <tr>
        <td><%= pis.get(i).getTitle()%></td>
        <td style="max-width: 10%;"><%= pis.get(i).getDescription()%></td>
        <td><%= pis.get(i).getCategory()%></td>
        <td>
            <%
                List<Vote> votes = VoteDB.getVotes(session3.getAttribute("username").toString());
                String up_color = "gray";
                String down_color = "gray";
                for (int j = 0; j < votes.size(); j++) {
                    if (votes.get(j).getVotedBy() == 1) {
                        if (votes.get(j).getInitiativeID() == pis.get(i).getId() && votes.get(j).getVote() == 0) {
                            down_color = "red";
                        } else if (votes.get(j).getInitiativeID() == pis.get(i).getId() && votes.get(j).getVote() == 1) {
                            up_color = "green";
                        }
                    }
                }
            %>
            <a onclick="add_vote('false', <%= pis.get(i).getId()%>)"><span  style="color:<%= down_color%>;" class="glyphicon glyphicon-thumbs-down"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a onclick="add_vote('true', <%= pis.get(i).getId()%>)"><span style="color:<%= up_color%>;" class="glyphicon glyphicon-thumbs-up"></span></a>

        </td>
    </tr>

    <%} else if (pis.get(i).getStatus() == 2) {;%>

    <tr>
        <td><%= pis.get(i).getTitle()%></td>
        <td style="max-width: 500px;"><%= pis.get(i).getDescription()%></td>
        <td><%= pis.get(i).getCategory()%></td>
        <%
            List<Vote> votes = VoteDB.getAllVotes();
            int upvotes = 0;
            int downvotes = 0;
            for (int j = 0; j < votes.size(); j++) {
                if (votes.get(j).getInitiativeID() == pis.get(i).getId()) {
                    if (votes.get(j).getVote() == 1) {
                        upvotes++;
                    } else {
                        downvotes++;
                    }
                }
            }
        %>
        <td>upvotes:<%= upvotes%> / downvotes:<%= downvotes%> </td>
    </tr>
    <% }%>
    <% }%>
</table>
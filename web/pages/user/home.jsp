<%-- 
    Document   : profile
    Created on : May 5, 2018, 11:20:58 AM
    Author     : julian
--%>

<%@page import="models.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% User user = (User) session.getAttribute("user");%>
<% ArrayList<Post> posts = (ArrayList<Post>) session.getAttribute("posts"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
        <meta charset="utf-8">
        <title><%= user.getName()%> - Profile</title>
    </head>
    <body class="logged-body">
        <div id="app" class="logged-container">
            <header>
                <div class="user__info">
                    <a href="/socialapp/profile?id=${user.id}">
                        <div class="user__picture">
                            <img src="/assets/dist/img/users/<%=user.getPicturePath()%>"
                                 alt="<%= user.getName()%>">
                        </div>
                        <span class="user__name">
                            ${user.name}
                        </span>
                    </a>
                </div>
                <div class="user__links">
                    <ul class="user__menu">
                        <li class="menu__item">
                            <a href="/socialapp/home?user=<%= user.getId()%>">Inicio</a> 
                        </li>
                        <li class="menu__item">
                            <a href="/socialapp/profile?page=list&id=<%=user.getId()%>">Mi lista</a>
                        </li>
                        <li class="menu__item" id="logout">
                            <a href="/socialapp/profile?page=logout&id=<%=user.getId()%>">Salir</a>
                        </li>
                    </ul>

                </div>
            </header>
            <section class="posts">
                <% if(posts != null || posts.size() == 0) { %>
                    <div class="posts__list">
                        
                    </div>
                <% } else { %>
                    <div id="no-posts" class="alert alert-info">
                        <p>No hay posts disponibles</p>
                    </div>
                <% } %>
            </section>
        </div>
    </body>
</html>

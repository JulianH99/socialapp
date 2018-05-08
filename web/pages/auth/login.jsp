<%-- 
    Document   : login
    Created on : Apr 25, 2018, 1:36:00 PM
    Author     : julian
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% Map<String, String> errors = (Map) session.getAttribute("errors"); %>

<html>
    <head>
        <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
        <meta charset="utf-8">
        <link rel="stylesheet" href="assets/dist/css/logreg.css">
        <title>Red Social De Otokos - Login</title>
    </head>
    <body class="auth-body">
        <div id="app" class="auth-container">
            <header>
                <img alt="logo" src="./assets/dist/img/happy.png" class="header__logo">
                <span >Red social de otokos</span>
            </header>
            <div class="login two-panel">
                <section class="section init__form-container">
                    <div class="section__title">
                        <h2 class="section__title-primary">Ingresa! :)</h2>
                        <small class="section__title-small"></small>
                    </div>
                    <form class="login__form section__form" method="POST"
                          action="/socialapp/login" autocomplete="off">
                        <div class="form__group">
                            <label class="form__label" for="username">Namae
                                <% if(errors != null && 
                                        errors.containsKey("username")) { %>
                                    <span class="error-message">
                                        <%= errors.get("username") %>
                                    </span>
                                <% } %>
                            </label>
                            <input class="form__input" id="username" name="username"
                                   required autofocus="true">
                        </div>
                        <div class="form__group">
                            <label class="form__label" for="password">Contraseña
                                <% if(errors != null && 
                                        errors.containsKey("password")) { %>
                                    <span class="error-message">
                                        <%= errors.get("password") %>
                                    </span>
                                <% } %>
                            </label>
                            <input class="form__input" id="password" name="password"
                                   type="password" required>
                        </div>
                        <div class="form__group button__group">
                            <a href="/socialapp/register" class="button button--transparent" id="reg-link">
                                Registrarse
                            </a>
                            <button type="submit" class="button button--round button--primary">
                                Ingresar
                            </button>
                        </div>
                    </form>

                </section>
                <section class="section image__container">
                    <img class="section__image" alt="Harui Suzumiya"
                         src="assets/dist/img/haruhi.png" id="haruhi">
                </section>
            </div>
        </div>
        <script src="./assets/dist/main.js"></script>
    </body>
</html>


<%-- 
    Document   : register
    Created on : 26/04/2018, 10:15:22 AM
    Author     : Estudiantes
--%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% Map<String, String> errors = (Map) session.getAttribute("errors");

    pageContext.setAttribute("errors", errors);
%>

<!doctype html>
<html>
    <head>
        <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
        <meta charset="utf-8">
        <link rel="stylesheet" href="assets/dist/css/logreg.css">
        <title>Red Social De Otokos - Register</title>
    </head>
    <body class="auth-body" id="auth-body">
        <div id="app" class="auth-container">
            <header>
                <img alt="logo" src="./assets/dist/img/happy.png" class="header__logo">
                <span >Red social de otokos</span>
            </header>
            <div class="login two-panel">
                <section class="section init__form-container">
                    <div class="section__title">
                        <h2 class="section__title-primary">Registrate! :)</h2>
                        <small class="section__title-small">Es gratis, y siempre lo será</small>
                    </div>
                    <form class="login__form section__form" method="POST"
                          action="/socialapp/register" autocomplete="off">
                        <div class="form__group">
                            <label class="form__label" for="username">
                                Tu namae
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
                            <label class="form__label" for="email">Tu correo
                                <% if(errors != null && 
                                        errors.containsKey("email")) { %>
                                    <span class="error-message">
                                        <%= errors.get("email") %>
                                    </span>
                                <% } %>
                            </label>
                            <input class="form__input" id="email" name="email"
                                   required>
                        </div>
                        <div class="form__group">
                            <label class="form__label" for="password">Tu contraseña
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
                        <div class="form__group">
                            <label class="form__label" for="password_confirmation">
                                Tu contraseña (otra vez)</label>
                            <input class="form__input" id="password_confirmation" 
                                   name="password_confirmation" type="password" required>
                        </div>
                        <div class="form__group">
                            <label class="form__label">Escoge tu genero
                                <% if(errors != null && 
                                        errors.containsKey("gender")) { %>
                                    <span class="error-message">
                                        <%= errors.get("gender") %>
                                    </span>
                                <% } %>
                            </label>
                            <div class="radio__group">
                                <div class="radio-input">
                                    <input type="radio" id="male" name="gender" required>
                                    <label class="radio-input__label"
                                           for="male">Masculino</label>
                                </div>
                                <div class="radio-input">
                                    <input type="radio" id="female" name="gender"
                                           required>
                                    <label class="radio-input__label"
                                           for="female">Femenino</label>
                                </div>
                                <div class="radio-input">
                                    <input type="radio" id="trap" name="gender"
                                           required>
                                    <label class="radio-input__label"
                                           for="trap">Trapito</label>
                                </div>
                            </div>
                        </div>
                        <div class="form__group button__group">
                            <div id="terms-container" class="button button--transparent">
                                <input type="checkbox" class="checkbox" id="terms">
                                <label for="terms">
                                    <a href="terminos" id="terms-link">
                                        Acepto los terminos y condiciones
                                    </a>
                                </label>
                            </div>
                            <button type="submit" class="button button--round button--primary">
                                Registrarse
                            </button>
                        </div>
                    </form>

                </section>
                <section class="section image__container">
                    <img class="section__image" alt="Rias Gremory"
                         src="assets/dist/img/rias.png" id="rias">
                </section>
            </div>
        </div>
        <script src="./assets/dist/particles.min.js"></script>
        <script>
            particlesJS.load('auth-body', './assets/dist/particlesjs-config.json'
                    , function() {
                        document.querySelector('.particles-js-canvas-el')
                                .style.setProperty("--height", 
                        document.body.clientHeight + 'px');
                    });
        </script>
        <script src="./assets/dist/main.js"></script>
        
        
    </body>
</html>

<%-- 
    Document   : login
    Created on : Apr 25, 2018, 1:36:00 PM
    Author     : julian
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>


<% Map<String, String> errors = (Map) session.getAttribute("errors");

    pageContext.setAttribute("errors", errors);
%>


<l:loglayout title="Login">
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
                        <c:if test="pageContext.errors.containsKey('username')">
                            <span class="error-message">
                                <c:out value="${pageContent.errors.get('username')}"/>
                            </span>
                        </c:if>
                    </label>
                    <input class="form__input" id="username" name="username"
                           required autofocus="true">
                </div>
                <div class="form__group">
                    <label class="form__label" for="password">Contraseña
                        <c:if test="pageContext.errors.containsKey('password')">
                            <span class="error-message">
                                <c:out value="${pageContent.errors.get('password')}"/>
                            </span>
                        </c:if>
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
</l:loglayout>


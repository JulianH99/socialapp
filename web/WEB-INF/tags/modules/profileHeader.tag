<%-- 
    Document   : profileHeader
    Created on : 26/04/2018, 10:47:00 AM
    Author     : Estudiantes
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- TODO: implement type --%>
<%@attribute name="user" required="true"%>

<header>
    <div class="user__info">
        <a href="/socialapp/profile?id=${user.id}">
            <div class="user__picture">
                <img src="/assets/dist/img/${user.picturePath}" alt="${user.fullName}">
            </div>
            <span class="user__name">
                ${user.name}
            </span>
        </a>
    </div>
    <div class="user__links">
        <ul class="user__menu">
            <li class="menu__item">
                <a href="/socialapp/profile?page=list&id=${user.id}">Mi lista</a>
            </li>
            <li class="menu__item" id="logout">
                <a href="/socialapp/profile?page=logout&id=${user.id}">Salir</a>
            </li>
        </ul>
                
    </div>
</header>
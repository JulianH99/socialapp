<%-- 
    Document   : profilePost
    Created on : Apr 26, 2018, 7:00:33 PM
    Author     : julian
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="post" required="true" %>
<%@attribute name="comments" type="java.util.ArrayList" %>

<div class="post">
    <section class="post__user">
        <div class="img__container">
            <img class="post__user-picture" src="/assets/img/users/${user.picture}">
        </div>
        <span class="post__user-name">
            ${user.fullName}
        </span>
    </section>
    <section class="post__content">
        <div class="content__text-calification">
            <p>${post.content}</p>
            <div class="content__calification">
                <!-- TODO: implement starts -->
            </div>
        </div>
        <div class="post__img img__container">
            <!-- TODO: make if statement for post.type -->
        </div>
    </section>
    <section class="post__comments">
        <!-- TODO: loop through comments -->
    </section>
</div>
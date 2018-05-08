<%-- 
    Document   : profilelayout
    Created on : 26/04/2018, 10:34:21 AM
    Author     : Estudiantes
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="inc" tagdir="/WEB-INF/tags/inc" %>
<%@taglib prefix="module" tagdir="/WEB-INF/tags/modules" %>
<%@attribute name="user" required="true" type="models.User" %>
<%-- The list of normal or fragment attributes can be specified here: --%>

<html>
    <inc:head title="${user.fullName} profile"/>
    <body>
        <div id="app">
            <module:profileHeader user="${user}"></module:profileHeader>
            <main>
                <jsp:doBody/>
            </main>
        </div>
    </body>
    
</html>
<%-- 
    Document   : loglayout
    Created on : Apr 25, 2018, 12:37:16 PM
    Author     : julian
--%>

<%@tag description="Layout wraper for login and register pages" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<%@attribute name="title" type="String" %>
<%@taglib prefix="inc" tagdir="/WEB-INF/tags/inc" %>

<!doctype html>
<html>
    <inc:head stylePath="" title="${title}"></inc:head>
    <body>
        <div id="app">
            <header>
                <img alt="logo" src="./assets/dist/img/happy.png" class="header__logo">
                <span >Red social de otokos</span>
            </header>
            <jsp:doBody></jsp:doBody>
        </div>
        
    </body>
    
</html>
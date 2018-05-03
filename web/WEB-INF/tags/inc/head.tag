<%-- 
    Document   : header
    Created on : Apr 25, 2018, 1:11:14 PM
    Author     : julian
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="stylePath"%>
<%@attribute name="title" %>

<%-- any content can be specified here e.g.: --%>
<head>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <meta charset="utf-8">
    <link rel="stylesheet" href="${stylePath}">
    <title>${title}</title>
</head>
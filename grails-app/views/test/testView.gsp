<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Test View</title>
</head>
<body>


<%-- This is my comment --%>

<% out << "Hello GSP! " %>
<br/>
<%= " *******************************************" %>
<br/>
<%= "Hello GSP! " %>
<br/>
<%= " *******************************************" %>
<br/>
<% now = new Date() %>
<%= now %>
<br/>
<%= " ********************LOOP***********************" %>
<br/>

<% [1,2,3,4].each { num -> %>
<p><%="Hello ${num}!" %></p>
<%}%>

<%= " ********************LOOP***********************" %>
<br/>

</body>
</html>

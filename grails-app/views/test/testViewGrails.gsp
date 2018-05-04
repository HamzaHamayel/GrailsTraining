<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Test View Grails</title>
</head>
<body>

**************read server variable**********************
<%-- read server variable --%>
<p>Hello ${params.name}</p>
<p>users:  ${users}</p>


**************tag lib**********************
<%-- tag lib --%>
<g:example value="test1"/>

<g:example value="test2">
    </p>body value</p>
</g:example>


****************create server variable********************
<%-- create server variable --%>
<g:set var="now" value="${new Date()}" />
<g:set var="myHTML">
    Some re-usable code on: ${new Date()}
</g:set>

<p>now: ${now}</p>
<p>myHTML: ${myHTML}</p>

****************if and else********************

<g:if test="${role == 'admin'}">
    <p>i'm admin</p>
</g:if>
<g:elseif test="${role == 'user'}">
    <p>i'm user</p>
</g:elseif>
<g:else>
    <p>i'm another</p>
</g:else>

****************each********************
<g:each in="${[1,2,3]}" var="num">
    <p>Number ${num}</p>
</g:each>

****************while********************
<g:set var="num" value="${1}" />
<g:while test="${num < 5 }">
    <p>Number ${num++}</p>
</g:while>

******************findAll******************
<g:findAll in="${users}" expr="it.applicationName == 'APP_2'">
    <p>userId: ${it.userId}</p>
</g:findAll>


*****************grep******************
<g:grep in="${users.userId}" filter="~/.*?al.*?/">
    <p>userId: ${it}</p>
</g:grep>


******************Links******************
<g:link action="testView" ><p>test view</p></g:link>

<g:link controller="test"><p>Test Home</p></g:link>

<g:link controller="user" action="list"><p>user List</p></g:link>


<g:link controller="user" action="show" id="1"><p>user 1</p></g:link>


<g:link controller="user" action="show" id="${user?.id}"><p>${user?.userId}</p></g:link>


<g:link url="[action: 'list', controller: 'user']"><p>user List</p></g:link>

<g:link params="[sort: 'fullName', order: 'asc', id:profile?.id]"
        controller="profile" action="list"><p>profile List</p></g:link>

*****************form*******************
%{--<g:form name="myForm" url="[controller:'book',action:'list']">--}%
<g:form name="myForm" controller="user" action="save">
    <g:textField name="name" value="123" />
    <g:actionSubmit value="save" action="save" />
</g:form>

****************Image********************
<br />
<img src="${createLinkTo(dir: 'images', file: 'grails.svg')}" />
<br />
<img src="<g:createLinkTo dir="images" file="favicon.ico" />" />
<br />

***************HTML from Server*********************
<p>${raw(textFieldValue)}</p>

</body>
</html>

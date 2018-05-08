<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="table" />
    <g:set var="entityName" value="${message(code: 'country.label', default: 'Country')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>


<a href="#list-country" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-country" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>

<g:render template="/template/shared/dataTable" model="[
        controller:'country',
        action:'filter',
        columns:[
                [key:'id',value:'id'],
                [key:'code',value:message(code:'country.code.label',default: 'code')],
                [key:'name',value:message(code:'country.name.label',default: 'name')],
        ]
]"/>




</body>
</html>
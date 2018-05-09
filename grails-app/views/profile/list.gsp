<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="table" />
    <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-profile" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>



    <g:form name="searchForm" >

        <fieldset class="form">
        fullName: <g:textField name="fullName" />
        salary: <g:textField name="salary" />
        bio: <g:textField name="bio" />
        email: <g:textField name="email" />
        timezone: <g:textField name="timezone" />
        address: <g:textField name="address" />
        applicationName: <g:textField name="applicationName" />
        </fieldset>

        <button type="button" onclick="_myDataTable.draw();" >search</button>
    </g:form>


    <g:render template="/template/shared/dataTable" model="[
            controller:'profile',
            action:'filter',
            searchFrom:'searchForm',
            columns:[
                    [key:'id',value:'id',visible:'false'],
                    [key:'user',value:message(code:'profile.user.label',default: 'user')],
                    [key:'fullName',value:message(code:'profile.fullName.label',default: 'fullName')],
                    [key:'bio',value:message(code:'profile.bio.label',default: 'bio')],
                    [key:'email',value:message(code:'profile.email.label',default: 'email')],
                    [key:'timezone',value:message(code:'profile.timezone.label',default: 'timezone')],
                    [key:'country',value:message(code:'profile.country.label',default: 'country')],
                    [key:'salary',value:message(code:'profile.salary.label',default: 'salary')],
                    [key:'dateOfBirth',value:message(code:'profile.dateOfBirth.label',default: 'dateOfBirth')],
            ]
    ]"/>

</div>
</body>
</html>
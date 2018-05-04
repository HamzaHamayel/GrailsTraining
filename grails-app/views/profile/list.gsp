<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
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




    <table>
        <thead>
        <tr>


            <th>
                <g:message code="profile.user.label" default="user" />
            </th>


            <th>
                <g:message code="profile.fullName.label" default="fullName" />
            </th>


            <th>
                <g:message code="profile.bio.label" default="bio" />
            </th>


            <th>
                <g:message code="profile.email.label" default="email" />
            </th>

            <th>
                <g:message code="profile.timezone.label" default="timezone" />
            </th>


            <th>
                <g:message code="profile.country.label" default="country" />
            </th>


            <th>
                <g:message code="profile.dateOfBirth.label" default="dateOfBirth" />
            </th>


            <th>
            </th>



        </tr>
        </thead>
        <tbody>


        <g:each in="${profileList}" var="profile" status="index">
            <tr class="${index%2 == 0 ? 'even': 'odd'}">
                <td>${profile?.user}</td>
                <td>${profile?.bio}</td>
                <td>${profile?.email}</td>
                <td>${profile?.timezone}</td>
                <td>${profile?.address}</td>
                <td>${profile?.country}</td>
                <td>${profile?.dateOfBirth}</td>
                <td>
                    <g:link action="show" id="${profile?.id}" >
                        <g:message code="default.show.label" args="${message(code:'profile.label')}"/>
                    </g:link>
                </td>

            </tr>

        </g:each>

        </tbody>
    </table>



    %{--<f:table collection="${profileList}" />--}%

    <div class="pagination">
        <g:paginate total="${profileCount ?: 0}" />
    </div>
</div>
</body>
</html>
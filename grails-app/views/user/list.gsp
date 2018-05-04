<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>


            <table>
                <thead>
                <tr>


                    <th>
                        <g:message code="user.userId.label" default="userId" />
                    </th>


                    <th>
                        <g:message code="user.homepage.label" default="homepage" />
                    </th>


                    <th>
                        <g:message code="user.applicationName.label" default="applicationName" />
                    </th>

                    <th>
                    </th>



                </tr>
                </thead>
                <tbody>


                <g:each in="${userList}" var="user" status="index">
                    <tr class="${index%2 == 0 ? 'even': 'odd'}">
                        <td>${user?.userId}</td>
                        <td>${user?.homepage}</td>
                        <td>${user?.applicationName}</td>
                        <td>
                            <g:link action="show" id="${user?.id}" >
                                <g:message code="default.show.label" args="${message(code:'user.label',default: 'user')}"/>
                            </g:link>
                        </td>

                    </tr>

                </g:each>

                </tbody>
            </table>



            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
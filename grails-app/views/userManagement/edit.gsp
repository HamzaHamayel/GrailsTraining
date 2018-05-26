<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="resourceForm" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <g:set var="title" value="${message(code:"default.edit.label",args:[entityName])}" />
        <title>${title}</title>
    </head>
    <body>


    <layout:formPanel color="success" title="${title}" name="userForm" action="update" method="PUT" buttons="[
            btn.submit(color:'primary',label:message(code: 'default.button.update.label', default: 'Update')),
    ]">

        <alert:errorListAlert errorsObject="${user}" />
        <alert:responseAlert />

        <g:hiddenField name="id" value="${this.user?.id}" />
        <g:hiddenField name="version" value="${this.user?.version}" />
        <g:render template="/userManagement/form" model="[user:user]" />

    </layout:formPanel>

    </body>
</html>

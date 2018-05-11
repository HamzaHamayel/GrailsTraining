<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="resourceForm" />
        <g:set var="entityName" value="${message(code: 'userActivity.label', default: 'userActivity')}" />
        <g:set var="title" value="${message(code:"default.edit.label",args:[entityName])}" />
        <title>${title}</title>
    </head>
    <body>


    <layout:formPanel color="success" title="${title}" name="userForm" action="update" method="PUT" buttons="[
            btn.submit(color:'primary',label:message(code: 'default.button.update.label', default: 'Update')),
    ]">

        <alert:errorListAlert errorsObject="${userActivity}" />
        <alert:responseAlert />

        <g:hiddenField name="id" value="${this.userActivity?.id}" />
        <g:hiddenField name="version" value="${this.userActivity?.version}" />
        <g:render template="/userActivity/form" model="[userActivity:userActivity]" />

    </layout:formPanel>


        </div>
    </body>
</html>

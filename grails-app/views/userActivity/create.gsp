<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="resourceForm" />
    <g:set var="entityName" value="${message(code: 'userActivity.label', default: 'userActivity')}" />
    <g:set var="title" value="${message(code:"default.create.label",args:[entityName])}" />
    <title>${title}</title>
</head>
<body>



<layout:formPanel color="success" title="${title}" name="userForm" action="save" method="POST" buttons="[
        btn.submit(color:'primary',label:message(code: 'default.button.create.label', default: 'Create')),
]">

    <alert:errorListAlert errorsObject="${userActivity}" />
    <alert:responseAlert />

    <g:render template="/userActivity/form" model="[userActivity:userActivity]" />

</layout:formPanel>
</body>
</html>

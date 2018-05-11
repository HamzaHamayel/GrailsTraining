<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="resourceForm" />
    <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
    <g:set var="title" value="${message(code: 'default.create.label',args: [entityName], default: 'Create Profile')}" />
    <title>${title}</title>
</head>
<body>


<layout:formPanel color="success" title="${title}" name="profileForm" action="save" method="POST" withAttachment="true" buttons="[
        btn.submit(color:'primary',label:message(code: 'default.button.create.label', default: 'Create')),
]">

    <alert:errorListAlert errorsObject="${profile}" />
    <alert:responseAlert />

    <g:render template="/profile/form" model="[profile:profile]" />

</layout:formPanel>


</body>
</html>

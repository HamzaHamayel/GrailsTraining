<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="resourceForm" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <g:set var="title" value="${message(code: 'default.edit.label',args: [entityName], default: 'Edit Profile')}" />
        <title>${title}</title>
    </head>
    <body>


            <layout:formPanel color="success" title="${title}" name="profileForm" action="update" withAttachment="true" buttons="[
                    btn.submit(color:'primary',label:message(code: 'default.button.edit.label', default: 'Edit')),
            ]">

                <alert:errorListAlert errorsObject="${profile}" />
                <alert:responseAlert />

                <g:hiddenField name="id" value="${profile?.id}" />
                <g:hiddenField name="version" value="${profile?.version}" />


                <g:render template="/profile/form" model="[profile:profile]" />

            </layout:formPanel>





        </div>
    </body>
</html>

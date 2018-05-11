<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="resourceShow" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <g:set var="title" value="${message(code: 'default.show.label',args: [entityName], default: 'Show Profile')}" />
        <title>${title}</title>
    </head>
    <body>


    <layout:showPanel title="${title}" buttons="[
            btn.blue(label:message(code: 'default.list.label',args: [entityName], default: 'Create'),href:createLink(action: 'list')),
            btn.orange(label:message(code: 'default.edit.label',args: [entityName], default: 'Create'),href:createLink(action: 'edit',id:profile?.id)),
            btn.red(label:message(code: 'default.delete.label',args: [entityName], default: 'Delete'),href:createLink(action: 'delete',id:profile?.id),method:'DELETE'),
    ]">


        <field:staticText label="${message(code: 'profile.id.label',default: 'id')}" value="${profile?.id}" />
        <field:staticText label="${message(code: 'profile.fullName.label',default: 'fullName')}" value="${profile?.fullName}" />



        <field:staticText label="${message(code: 'profile.bio.label',default: 'bio')}" value="${profile?.bio}" />
        <field:staticText label="${message(code: 'profile.email.label',default: 'email')}" value="${profile?.email}" />
        <field:staticText label="${message(code: 'profile.address.label',default: 'address')}" value="${profile?.address}" />

        <field:staticText label="${message(code: 'profile.timezone.label',default: 'address')}" value="${profile?.timezone}" />
        <field:staticText label="${message(code: 'profile.salary.label',default: 'address')}" value="${profile?.salary}" />
        <field:staticText label="${message(code: 'profile.age.label',default: 'age')}" value="${profile?.age}" />


        <field:staticText label="${message(code: 'profile.dateOfBirth.label',default: 'dateOfBirth')}" value="${profile?.dateOfBirth?.format("dd/MM/yyyy")}" />
        <field:staticText label="${message(code: 'profile.country.label',default: 'country')}" value="${profile?.country?.name}" />
        <field:staticText label="${message(code: 'profile.user.label',default: 'user')}" value="${profile?.user?.userId}" />

    <field:staticText label="${message(code: 'profile.photo.label',default: 'photo')}" value="${profile?.photo?"<img src=\"data:image/png;base64,${profile?.photo?.encodeBase64()}\" />":""}" />



    </layout:showPanel>



    </body>
</html>

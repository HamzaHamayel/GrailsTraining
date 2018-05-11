<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="resourceShow" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <g:set var="title" value="${message(code: 'default.show.label',args: [entityName], default: 'Show User')}" />
        <title>${title}</title>
    </head>
    <body>


    <layout:showPanel title="${title}" buttons="[
            btn.blue(label:message(code: 'default.list.label',args: [entityName], default: 'Create'),href:createLink(action: 'list')),
            btn.orange(label:message(code: 'default.edit.label',args: [entityName], default: 'Create'),href:createLink(action: 'edit',id:user?.id)),
            btn.red(label:message(code: 'default.delete.label',args: [entityName], default: 'Delete'),href:createLink(action: 'delete',id:user?.id),method:'DELETE'),
    ]">


        <field:staticText label="${message(code: 'user.id.label',default: 'id')}" value="${user?.id}" />
        <field:staticText label="${message(code: 'user.userId.label',default: 'user id')}" value="${user?.userId}" />
        <field:staticText label="${message(code: 'user.homepage.label',default: 'homepage')}" value="${user?.homepage}" />
        <field:staticText label="${message(code: 'user.applicationName.label',default: 'applicationName')}" value="${user?.applicationName}" />
        <field:staticText label="${message(code: 'user.dateCreated.label',default: 'dateCreated')}" value="${user?.dateCreated}" />
        <field:staticText label="${message(code: 'user.lastUpdated.label',default: 'lastUpdated')}" value="${user?.lastUpdated}" />


    </layout:showPanel>



    </body>
</html>

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
            btn.red(label:message(code: 'default.delete.label',args: [entityName], default: 'Delete'),href:createLink(action: 'delete',params:['user.id':userActivity?.user?.id,activityName:userActivity?.activityName]),method:'DELETE'),
    ]">


        <field:staticText label="${message(code: 'userActivity.user.label',default: 'user')}" value="${userActivity?.user?.username}" />
        <field:staticText label="${message(code: 'userActivity.activityName.label',default: 'activityName')}" value="${userActivity?.activityName}" />


    </layout:showPanel>



    </body>
</html>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="resourceList" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <g:set var="title" value="${message(code:'default.list.label',args:[entityName])}" />
    <title>${title}</title>
</head>
<body>


<alert:responseAlert />


<layout:collapsePanel id="searchPanel" opened="true" title="${message(code:'default.search.label',args: [entityName])}" buttons="[
        btn.blue(onclick:'_myDataTable.draw();',label:'Search'),
        btn.orange(onclick:'$(\'#searchForm\')[0].reset();_myDataTable.draw();',label:'Reset'),
]">


    <g:form name="searchForm" >

        <div class="col-lg-6">


            <field:text name="username" label="${message(code:'userActivity.user.label',default: 'User')}"  />

            <field:text name="activityName" label="${message(code:'userActivity.activityName.label',default: 'activityName')}"  />

        </div>

    </g:form>



</layout:collapsePanel>



<g:render template="/template/shared/dataTable" model="[
        controller:'userActivity',
        action:'filter',
        title:title,
        searchFrom:'searchForm',
        columns:[
                [key:'user',value:message(code:'userActivity.user.label',default: 'user')],
                [key:'activityName',value:message(code:'userActivity.activityName.label',default: 'activityName')],
        ],
        idComp:['user','activityName']
]"/>


</body>
</html>
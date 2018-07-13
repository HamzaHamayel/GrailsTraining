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


<layout:collapsePanel class="3333" id="searchPanel" opened="flase" title="${message(code:'default.search.label',args: [entityName])}" buttons="[
        btn.blue(onclick:'_myDataTable.draw();',label:'Search'),
        btn.orange(onclick:'$(\'#searchForm\')[0].reset();_myDataTable.draw();',label:'Reset'),
]">


    <g:form name="searchForm" >

        <div class="col-lg-6">


            <field:text name="id" label="${message(code:'user.id.label',default: 'Id')}"  />
            <field:text name="username" label="${message(code:'user.username.label',default: 'User Id')}"  />


        </div>

        <div class="col-lg-6">

            <field:text name="homepage" label="${message(code:'user.homepage.label',default: 'Homepage')}"  />
            <field:text name="applicationName" label="${message(code:'user.applicationName.label',default: 'Application Name')}"  />

        </div>

    </g:form>



</layout:collapsePanel>


<jasper:jasperReport
        jasper="sample-jasper-plugin"
        format="PDF,HTML,XML,CSV,XLS,RTF,TEXT,ODT,ODS,DOCX,XLSX,PPTX"
        name="Parameter Example" delimiter="|" >
    Your name: <input type="text" name="name"/>
    Your age: <input type="text" name="schconid"/>
</jasper:jasperReport>


%{--<g:jasperForm controller="userManagement" action="report" jasper="user-report" >--}%
    %{--<g:jasperButton format="pdf" jasper="user-report" text="PDF" />--}%
%{--</g:jasperForm>--}%


<g:render template="/template/shared/dataTable" model="[
        controller:'userManagement',
        action:'filter',
        title:title,
        searchFrom:'searchForm',
        columns:[
                [key:'id',value:'id'],
                [key:'username',value:message(code:'user.username.label',default: 'username')],
                [key:'homepage',value:message(code:'user.homepage.label',default: 'homepage')],
                [key:'applicationName',value:message(code:'user.applicationName.label',default: 'applicationName')],
        ]
]"/>


</body>
</html>
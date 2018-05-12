<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="resourceList" />
    <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
    <g:set var="title" value="${message(code: 'default.list.label',args: [entityName], default: 'Profile List')}" />
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


            <field:text name="fullName" label="${message(code:'profile.fullName.label',default: 'fullName')}"  />
            <field:mail name="email" label="${message(code:'profile.email.label',default: 'email')}"  />

        </div>

        <div class="col-lg-6">

            <field:currency name="salary" label="${message(code:'profile.salary.label',default: 'salary')}"  />
            <field:text name="bio" label="${message(code:'profile.bio.label',default: 'bio')}"  />

        </div>


        <div class="col-lg-6">

            <field:text name="timezone" label="${message(code:'profile.timezone.label',default: 'timezone')}"  />
            <field:text name="address" label="${message(code:'profile.address.label',default: 'address')}"  />

        </div>

        <div class="col-lg-6">

            <field:text name="applicationName" label="${message(code:'user.applicationName.label',default: 'applicationName')}"  />
            <field:date name="dateOfBirth" label="${message(code:'profile.dateOfBirth.label',default: 'dateOfBirth')}"  />

        </div>

    </g:form>



</layout:collapsePanel>




    <g:render template="/template/shared/dataTable" model="[
            controller:'profile',
            action:'filter',
            searchFrom:'searchForm',
            title:title,
            columns:[
                    [key:'id',value:'id'],
                    [key:'user',value:message(code:'profile.user.label',default: 'profile')],
                    [key:'fullName',value:message(code:'profile.fullName.label',default: 'fullName')],
//                    [key:'bio',value:message(code:'profile.bio.label',default: 'bio')],
                    [key:'email',value:message(code:'profile.email.label',default: 'email')],
//                    [key:'timezone',value:message(code:'profile.timezone.label',default: 'timezone')],
                    [key:'country',value:message(code:'profile.country.label',default: 'country')],
                    [key:'salary',value:message(code:'profile.salary.label',default: 'salary')],
                    [key:'dateOfBirth',value:message(code:'profile.dateOfBirth.label',default: 'dateOfBirth')],
            ]
    ]"/>


</body>
</html>
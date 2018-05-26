
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="resourceForm" />
    <g:set var="entityName" value="Acl" />
    <g:set var="title" value="${message(code:"default.create.label",args:[entityName])}" />
    <title>${title}</title>
</head>
<body>



<layout:formPanel color="success" title="${title}" name="userForm" action="saveAcl" method="POST" buttons="[
        btn.submit(color:'primary',label:message(code: 'default.button.create.label', default: 'Create')),
]">

    <alert:responseAlert />



    <div class="col-lg-6">
        <field:select name="countryId" label="Country" from="${edu.training.Country.list()}" optionKey="id" optionValue="name" noSelection="['':'']"/>
        <field:select name="userId" label="User" from="${edu.training.security.User.list()}" optionKey="username" optionValue="username" noSelection="['':'']"/>


    </div>
    <div class="col-lg-6">


        <field:select label="Permission" name="permission" from="${['read','write']}" noSelection="['':'']"/>
    </div>

</layout:formPanel>

</body>
</html>
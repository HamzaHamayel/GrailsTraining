


<div class="col-lg-6">
    <field:text name="userId" object="${user}" label="${message(code:'user.userId.label',default: 'User Id')}" required="true" value="${user?.userId}" />
    <field:password name="password" object="${user}" label="${message(code:'user.password.label',default: 'Password')}" required="true" value="${user?.password}" />

</div>

<div class="col-lg-6">

    <field:text name="homepage" object="${user}"  label="${message(code:'user.homepage.label',default: 'Homepage')}" value="${user?.homepage}" />
    <field:text name="applicationName" object="${user}"  label="${message(code:'user.applicationName.label',default: 'applicationName')}" value="${user?.applicationName}" />


</div>





<div class="col-lg-6">
    <field:text name="fullName" object="${profile}"
                label="${message(code:'profile.fullName.label',default: 'fullName')}"
                required="true" value="${profile?.fullName}" />

    <field:mail name="email" object="${profile}"
                label="${message(code:'profile.email.label',default: 'email')}"
                required="true" value="${profile?.email}" />

</div>

<div class="col-lg-6">

    <field:text name="bio" object="${profile}"
                label="${message(code:'profile.bio.label',default: 'bio')}"
                value="${profile?.bio}" />


    <field:text name="timezone" object="${profile}"
                label="${message(code:'profile.timezone.label',default: 'bio')}"
                value="${profile?.timezone}" />



</div>


<div class="col-lg-6">



    <field:text name="address" object="${profile}"
                label="${message(code:'profile.address.label',default: 'address')}"
                value="${profile?.address}" />



    <field:currency name="salary" object="${profile}"
                label="${message(code:'profile.salary.label',default: 'salary')}"
                value="${profile?.salary}" />

</div>


<div class="col-lg-6">


    <field:date required="true" name="dateOfBirth" object="${profile}"
                   label="${message(code:'profile.dateOfBirth.label',default: 'dateOfBirth')}"
                   value="${profile?.dateOfBirth?.format("dd/MM/yyyy")}" />



    %{--<field:select required="true" name="country.id" object="${profile}" from="${edu.training.Country.list()}"--}%
                %{--optionKey="id" optionValue="name"--}%
                  %{--label="${message(code:'profile.country.label',default: 'country')}"--}%
                %{--value="${profile?.country?.id}" />--}%


    <g:render template="/template/shared/autoComplete"
              model="[
                      controller:'country',
                      required:true,
                      label:message(code:'profile.country.label',default: 'country'),
                      object:profile,
                      fieldHiddenId:'countryId',
                      fieldHiddenName:'country.id',
                      fieldTextName:'country',
                      fieldHiddenValue:profile?.country?.id,
                      fieldTextValue:profile?.country?.name
              ]"
    />

</div>



<g:set var="userList" value="${ edu.training.security.User.findAllByIdNotInList(edu.training.Profile.list()?.user?.id)}"/>
<g:if test="${profile?.id != null}">
    <g:set var="userList" value="${[profile?.user]}"/>
</g:if>

<div class="col-lg-6">


    <field:select required="true" name="user.id" object="${profile}" from="${userList}"
                  optionKey="id" optionValue="username" disabled="${(profile?.id)?"true":"false"}"
                  label="${message(code:'profile.user.label',default: 'user')}"
                  value="${profile?.user?.id}" />


</div>


<div class="col-lg-6">
    <field:file name="multipartFile" object="${profile}"
                  label="${message(code:'profile.photo.label',default: 'photo')}"
                  value="${profile?.photo}" />

</div>
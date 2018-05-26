
<g:set var="objectName" value="profile" />

<fieldset class="form">


    <g:render template="/template/shared/uploadComponent"

              model="[
                      object:profile,
                      objectKey:objectName,
                      fieldName:'multipartFile',
                      fieldValueName:'photo'
              ]"
    />


    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:profile,
                      objectKey:objectName,
                      fieldName:'fullName',
                      fieldValue:profile?.fullName
              ]"
    />


    <g:render template="/template/shared/textComponent"

              model="[
                      object:profile,
                      objectKey:objectName,
                      fieldName:'bio',
                      fieldValue:profile?.bio
              ]"
    />

    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:profile,
                      objectKey:objectName,
                      fieldName:'email',
                      fieldValue:profile?.email
              ]"
    />


    <g:render template="/template/shared/textComponent"

              model="[
                      object:profile,
                      objectKey:objectName,
                      fieldName:'timezone',
                      fieldValue:profile?.timezone
              ]"
    />


    <g:render template="/template/shared/textComponent"

              model="[
                      object:profile,
                      objectKey:objectName,
                      fieldName:'address',
                      fieldValue:profile?.address
              ]"
    />
    <g:render template="/template/shared/textComponent"

              model="[
                      object:profile,
                      objectKey:objectName,
                      fieldName:'salary',
                      fieldValue:profile?.salary
              ]"
    />


    <g:render template="/template/shared/dateComponent"

              model="[
                      isRequired:true,
                      object:profile,
                      objectKey:objectName,
                      fieldName:'dateOfBirth',
                      fieldValue:profile?.dateOfBirth
              ]"
    />


    <g:render template="/template/shared/selectComponent"

              model="[
                      isRequired:true,
                      object:profile,
                      objectKey:objectName,
                      labelFieldName:'country',
                      fieldName:'country.id',
                      fieldValue:profile?.country?.id,
                      dataList:edu.training.Country.list(),
                      optionKey:'id',
                      optionValue:'name',
              ]"
    />

    <g:set var="userList" value="${ edu.training.security.User.findAllByIdNotInList(edu.training.Profile.list()?.user?.id)}"/>
    <g:if test="${profile?.id != null}">
        <g:set var="userList" value="${[profile?.user]}"/>
    </g:if>

    <g:render template="/template/shared/selectComponent"

              model="[
                      isDisabled:(profile?.id != null),
                      isRequired:true,
                      object:profile,
                      objectKey:objectName,
                      labelFieldName:'user',
                      fieldName:'user.id',
                      fieldValue:profile?.user?.id,
                      dataList:userList,
                      optionKey:'id',
                      optionValue:'username',
              ]"
    />


</fieldset>

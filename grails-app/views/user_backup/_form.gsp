
<g:set var="objectName" value="user" />

<fieldset class="form">


    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:user,
                      objectKey:objectName,
                      fieldName:'username',
                      fieldValue:user?.username
              ]"
    />


    <g:render template="/template/shared/passwordComponent"

              model="[
                      isRequired:true,
                      object:user,
                      objectKey:objectName,
                      fieldName:'password',
                      fieldValue:user?.password
              ]"
    />

    <g:render template="/template/shared/textComponent"

              model="[
                      object:user,
                      objectKey:objectName,
                      fieldName:'homepage',
                      fieldValue:user?.homepage
              ]"
    />


    <g:render template="/template/shared/textComponent"

              model="[
                      object:user,
                      objectKey:objectName,
                      fieldName:'applicationName',
                      fieldValue:user?.applicationName
              ]"
    />





</fieldset>

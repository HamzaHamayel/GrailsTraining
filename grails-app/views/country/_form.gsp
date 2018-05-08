
<g:set var="objectName" value="country" />

<fieldset class="form">


    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:country,
                      objectKey:objectName,
                      fieldName:'code',
                      fieldValue:country?.code
              ]"
    />


    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:country,
                      objectKey:objectName,
                      fieldName:'name',
                      fieldValue:country?.name
              ]"
    />


</fieldset>

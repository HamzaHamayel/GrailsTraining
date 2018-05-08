
<g:set var="objectName" value="tag" />

<fieldset class="form">


    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:tag,
                      objectKey:objectName,
                      fieldName:'name',
                      fieldValue:tag?.name
              ]"
    />


    <g:render template="/template/shared/selectComponent"

              model="[
                      isRequired:true,
                      object:tag,
                      objectKey:objectName,
                      labelFieldName:'user',
                      fieldName:'user.id',
                      fieldValue:tag?.user?.id,
                      dataList:edu.training.User.list(),
                      optionKey:'id',
                      optionValue:'userId',
              ]"
    />



</fieldset>

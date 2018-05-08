
<g:set var="objectName" value="post" />

<fieldset class="form">


    <g:render template="/template/shared/textComponent"

              model="[
                      isRequired:true,
                      object:post,
                      objectKey:objectName,
                      fieldName:'content',
                      fieldValue:post?.content
              ]"
    />


    <g:render template="/template/shared/selectComponent"

              model="[
                      isRequired:true,
                      object:post,
                      objectKey:objectName,
                      labelFieldName:'user',
                      fieldName:'user.id',
                      fieldValue:post?.user?.id,
                      dataList:edu.training.User.list(),
                      optionKey:'id',
                      optionValue:'userId',
              ]"
    />



</fieldset>

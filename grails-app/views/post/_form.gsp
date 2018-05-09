
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
                      fieldId:'userId',
                      fieldName:'user.id',
                      fieldValue:post?.user?.id,
                      dataList:edu.training.User.list(),
                      optionKey:'id',
                      optionValue:'userId',
                      onChange:'setUser()'
              ]"
    />





</fieldset>


<g:if test="${!post?.id}">
    <fieldset>

        <g:render template="/template/shared/textComponent"

                  model="[
                          fieldName:'tags[0].name',
                          labelValue:'tag 1'
                  ]"
        />


        <g:render template="/template/shared/textComponent"

                  model="[
                          fieldName:'tags[1].name',
                          labelValue:'tag 2'
                  ]"
        />


        <g:hiddenField id="user1" name="tags[0].user.id" />
        <g:hiddenField  id="user2" name="tags[1].user.id" />
    </fieldset>


    <script>
        function setUser() {
            $('#user1').val($('#userId').val());
            $('#user2').val($('#userId').val());
        }
    </script>
</g:if>
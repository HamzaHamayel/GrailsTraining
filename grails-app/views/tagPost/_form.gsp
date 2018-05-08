
<g:set var="objectName" value="tagPost" />

<fieldset class="form">


    <g:render template="/template/shared/autoCompleteComponent"

              model="[
                      controller:'tag',
                      isRequired:true,
                      object:tagPost,
                      objectKey:objectName,
                      fieldHiddenId:'tagId',
                      fieldHiddenName:'tag.id',
                      fieldTextName:'tagValue',
                      fieldName:'tag',
                      fieldHiddenValue:tagPost?.tag?.id,
                      fieldTextValue:tagPost?.tag?.name
              ]"
    />

    <g:render template="/template/shared/autoCompleteComponent"

              model="[
                      controller:'post',
                      isRequired:true,
                      object:tagPost,
                      objectKey:objectName,
                      fieldHiddenId:'postId',
                      fieldHiddenName:'post.id',
                      fieldTextName:'postValue',
                      fieldName:'post',
                      fieldHiddenValue:tagPost?.post?.id,
                      fieldTextValue:tagPost?.post?.content
              ]"
    />



</fieldset>

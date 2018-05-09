<g:render template="/template/shared/formScript" />

<g:render template="/template/shared/textComponent" model="[
        isRequired:isRequired,
        object:object,
        objectKey:objectKey,
        fieldName:fieldName,
        fieldRealName:fieldTextName,
        fieldValue:fieldTextValue,
        clazz:'basicAutoComplete'
]" />

<g:hiddenField id="${fieldHiddenId?:fieldHiddenName}" name="${fieldHiddenName}" value="${fieldHiddenValue}" />


<script type="text/javascript">
    $('#${fieldTextName}').autoComplete({
        resolverSettings: {
            url: '${createLink(controller: controller,action: 'autoComplete')}'
        }
    }).on('autocomplete.select', function (evt, item) {
        $("#${fieldHiddenId}").val(item.value);
    });
</script>
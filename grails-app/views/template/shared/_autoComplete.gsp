<field:text id="text_${fieldTextId?:fieldTextName}" name="text_${fieldTextName}" object="${object}"
            label="${label}" required="${required?:"false"}"
            value="${fieldTextValue}" icon="fa fa-search" />

<g:hiddenField id="${fieldHiddenId?:fieldHiddenName}" name="${fieldHiddenName}" value="${fieldHiddenValue}" />


<script type="text/javascript">
    $('#text_${fieldTextId?:fieldTextName}').autoComplete({
        resolver:'custom',
        minLength:0,
        events: {
            search: function (qry, callback) {
                // let's do a custom ajax call
                $.ajax(
                    '${createLink(controller: controller,action: 'autoComplete')}',
                    {
                        data: {
                            sSearch: qry
                        }
                    }
                ).done(function (res) {
                    callback(res)
                });
            }
        }
    }).on('autocomplete.select', function (evt, item) {
        $("#${fieldHiddenId}").val(item.value);
    });
</script>
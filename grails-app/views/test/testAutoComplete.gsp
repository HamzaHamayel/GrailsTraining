<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Test Auto Complete</title>
</head>

<body>


<div class="content">



    <div class="fieldcontain">



        <label>


            user:

            <g:textField name="user" class=" basicAutoComplete"  style="width:290px;" />
            <g:textField name="userId" />

        </label>


    </div>



    <div class="fieldcontain">



        <label>


            profile:

            <g:textField name="profile" class=" basicAutoComplete"  style="width:290px;" />

        </label>


    </div>


</div>

<asset:javascript src="jquery-2.2.0.min.js"/>

<asset:javascript src="bootstrap-autocomplete.min.js"/>


<script>
    $('#profile').autoComplete({
        resolver:'custom',
        minLength:0,
        events: {
            search: function (qry, callback) {
                // let's do a custom ajax call
                $.ajax(
                    '${createLink(controller: 'profile',action: 'autoComplete')}',
                    {
                        data: {
                            sSearch: qry,
                            userId: $('#userId').val()
                        }
                    }
                ).done(function (res) {
                    callback(res)
                });
            }
        }
    });



    $('#user').autoComplete({
        resolverSettings: {
            url: '${createLink(controller: 'user',action: 'autoComplete')}'
        },
        minLength:0
    }).on('autocomplete.select', function (evt, item) {
        $("#userId").val(item.value);
    });;





</script>



</body>
</html>

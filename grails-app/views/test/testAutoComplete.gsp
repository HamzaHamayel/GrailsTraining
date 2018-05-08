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


            text:

            <g:textField name="autoCom" class=" basicAutoComplete" autocomplete="off" style="width:290px;" />

        </label>


    </div>

</div>

<asset:javascript src="jquery-2.2.0.min.js"/>

<asset:javascript src="bootstrap-autocomplete.min.js"/>


<script>
    $('.basicAutoComplete').autoComplete({
        resolverSettings: {
            url: '${createLink(controller: 'profile',action: 'autoComplete')}'
        }
    });
</script>



</body>
</html>

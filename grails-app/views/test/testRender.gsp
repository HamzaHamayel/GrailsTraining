<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Test Render</title>

    <script type="text/javascript">
        function getData() {
            $.ajax({
                url: "${createLink(controller: 'test',action: 'renderTemplateOrTagLib')}",
                cache: false,
                type: "GET",
                data: {},
                dataType: "html",
                success: function(html){
                    $("#results").html(html);
                }
            });
        }
    </script>

</head>

<body>

<div id="results">
    
</div>

<button onclick="getData()">Get Data</button>

</body>
</html>

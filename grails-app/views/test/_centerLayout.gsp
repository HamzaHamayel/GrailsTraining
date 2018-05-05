<!doctype html>
<html>
<head>
    %{--<meta name="layout" content="custom"/>--}%
    <title>Test Layout</title>
</head>
<body>




<g:applyLayout name="fiveblocks">
    <head>
        <title><g:message code="title.homepage"/></title>
    </head>

    <content tag="banner1">
        <h1>Welcome to Grails Layout Demo</h1>
    </content>


    <content tag="right1">
        <p>right1</p>
    </content>

    <content tag="center">


        %{--<g:include controller="${controller}" action="${action}"/>--}%


    </content>


    <content tag="left2">
        <p>left2</p>
    </content>


</g:applyLayout>




</body>
</html>

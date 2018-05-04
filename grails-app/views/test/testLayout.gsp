<!doctype html>
<html>
<head>
    <meta name="layout" content="custom"/>
    <title>Test Layout</title>
</head>
<body>
%{--<g:applyLayout name="custom"  />--}%
%{--<p>new text after</p>--}%

%{--<g:applyLayout name="custom"  >--}%
    %{--<p>body of apply layout</p>--}%
    %{--<g:include controller="user" action="list" />--}%
%{--</g:applyLayout>--}%



%{--<g:applyLayout name="fiveblocks">--}%
    %{--<head>--}%
        %{--<title><g:message code="title.homepage"/></title>--}%
    %{--</head>--}%

    %{--<content tag="banner">--}%
        %{--<h1>Welcome to Grails Layout Demo</h1>--}%
    %{--</content>--}%

    %{--<content tag="left1">--}%
        %{--<p>left1</p>--}%
    %{--</content>--}%

    %{--<content tag="box-left">--}%
        %{--<p>box-left</p>--}%
    %{--</content>--}%

    %{--<content tag="box-right">--}%
        %{--<p>box-right</p>--}%
    %{--</content>--}%

    %{--<content tag="right1">--}%
        %{--<p>right1</p>--}%
    %{--</content>--}%
%{--</g:applyLayout>--}%


</body>
</html>

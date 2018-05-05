<!doctype html>
<html>
<head>
    %{--<meta name="layout" content="custom"/>--}%
    <title>Test Layout</title>
</head>
<body>

%{--<p>my body</p>--}%
%{--<g:applyLayout name="custom"  />--}%
%{--<p>new text after</p>--}%

%{--<g:applyLayout name="custom"  >--}%
    %{--<p>body of apply layout</p>--}%
    %{--<g:include controller="user" action="list" />--}%
%{--</g:applyLayout>--}%


<g:render template="centerLayout"  model="[controller:'user',action:'list']"/>



</body>
</html>

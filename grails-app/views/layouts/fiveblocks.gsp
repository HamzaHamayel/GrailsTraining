<html>
<head>
    <meta name="layout" content="page"/>
    <title><g:layoutTitle/></title>
    <g:layoutHead/>
    <style>
    #container{width:100%;}
    #left{float:left;width:100px;}
    #right{float:right;width:100px;}
    #center{margin:0 auto;width:100px;}
    </style>
</head>
<body>
<div id="banner">
    <g:pageProperty name="page.banner1"/>
</div>


<div id="container">

    <div id="left" style="border: 1px solid green;">
        <g:pageProperty name="page.left1"/>
        <g:pageProperty name="page.left2" />
        <g:pageProperty name="page.left3"/>

        <div id="box-left">
            <g:pageProperty name="page.box-left"/>
        </div>

        <div id="box-right">
            <g:pageProperty name="page.box-right"/>
        </div>
    </div>

    <div id="right" style="border: 1px solid blue;">
        <g:pageProperty name="page.right1"/>
        <g:pageProperty name="page.right2"/>
        <g:pageProperty name="page.right3"/>
    </div>

    <div id="center" style="border: 1px solid red;">
        <g:pageProperty name="page.center"/>
    </div>


</div>
</body>
</html>
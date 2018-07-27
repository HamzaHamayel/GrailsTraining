<!doctype html>
<html>
<head>
    <meta name="layout" content="resourceList"/>
    <title>Main Page</title>
</head>
<body>




<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Available Links</h1>

        <div id="controllers" role="navigation">
            <g:set var="actions" value="${grailsApplication.controllerClasses.find{it.logicalPropertyName == "test"}.clazz.methods.findAll { it.getAnnotation(grails.web.Action) }?.sort{it.name}}" />
            <ul>
                <g:each in="${actions}" var="actionIns">
                    <g:if test="${!(actionIns?.name in ['list','index','renderTemplateOrTagLib'])}">
                    <li class="controller">
                        <g:link action="${actionIns?.name}" >
                            ${actionIns?.name}
                        </g:link>
                    </li>
                    </g:if>
                </g:each>
            </ul>
        </div>
    </section>

</div>


</body>
</html>

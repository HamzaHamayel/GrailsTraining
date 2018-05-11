<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="show" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-profile" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>


            <li class="fieldcontain">
                <span id="dateOfBirth-label" class="property-label">
                    photo
                </span>
                <div class="property-value" aria-labelledby="photo-label">
                    <div id="imageView">
                        %{--<img src="data:image/png;base64,${profile?.photo.encodeBase64()}" />--}%
                    </div>
                </div>
            </li>


            <f:display bean="profile" except="photo" />



        </div>


            <g:form resource="${this.profile}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.profile}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>

    <g:if test="${profile?.id && profile?.photo}">
        <script type="text/javascript">
            var img = $("<img />").attr('src', '${createLink(controller: 'profile',action: 'loadImage')}/${profile?.id}')
                .on('load', function() {
                    if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
                        alert('broken image!');
                    } else {
                        $("#imageView").append(img);
                    }
                });
        </script>
    </g:if>

    </body>
</html>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="form" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="create-user" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>


    <g:hasErrors bean="${this.user}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <div id="messageStatus"></div>


    <g:form name="userForm" action="save" method="POST">

        <g:render template="/user/form" model="[user:user]" />
    %{--<g:render template="userTemplate" model="[user:user]" />--}%
    %{--<tmpl:userTemplate model="[user:user]" />--}%


        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>


<script type="text/javascript">
    jQuery(document).ready(function () {
        var options = {
            beforeSubmit:preSubmit, // pre-submit callback
            success:postSubmit, // post-submit callback
            dataType:"json",        // 'xml', 'script', or 'json' (expected server response type)
            // clearForm: true,       // clear all form fields after successful submit
            // resetForm: true        // reset the form after successful submit
            // $.ajax options can be used here too, for example:
            //timeout:   3000
        };
        jQuery('#userForm').ajaxForm(options);
    });
    // pre-submit callback
    function preSubmit(formData, jqForm, options) {
        // (formData): is an array; here we use $.param to convert it to a string to display it
        // but the form plugin does this for you automatically when it submits the data

        // (jqForm): is a jQuery object encapsulating the form element.  To access the
        // DOM element for the form do this:
        // var formElement = jqForm[0];

        // here we could return false to prevent the form from being submitted;
        // returning anything other than false will allow the form submit to continue
        // and we will validate all required inputs in the form
        return true;
    }
    function postSubmit(json, statusText, xhr, jqForm) {
        // for normal html responses, the first argument to the success callback
        // is the XMLHttpRequest object's responseText property

        // if the ajaxForm method was passed an Options Object with the dataType
        // property set to 'xml' then the first argument to the success callback
        // is the XMLHttpRequest object's responseXML property

        // if the ajaxForm method was passed an Options Object with the dataType
        // property set to 'json' then the first argument to the success callback
        // is the json data object returned by the server

//            alert('status: ' + statusText + 'responseText: ' + responseText +
        $('#messageStatus').empty();
        $('.fieldcontain').removeClass('error');
        var el;
        if (json.success) {
            // $('#userForm').clearForm();
            $('#userForm').resetForm();
            el = jQuery("#messageStatus").html(json.message)
        }
        else {
            el = jQuery('#messageStatus').html(json.message);
            $(json.errorList).each(function (index, value) {
                $("#fieldcontain_" + value).addClass("error");
            });
        }

        $('html,body').animate({scrollTop: $(el).offset().top - 100},'slow');


    }
</script>

</body>
</html>

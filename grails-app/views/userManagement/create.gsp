<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="resourceForm" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <g:set var="title" value="${message(code:"default.create.label",args:[entityName])}" />
    <title>${title}</title>
</head>
<body>



<layout:formPanel color="success" title="${title}" name="userForm" action="save" method="POST" buttons="[
        btn.submit(color:'primary',label:message(code: 'default.button.create.label', default: 'Create')),
]">

    <alert:errorListAlert errorsObject="${user}" />
    <alert:responseAlert />

    <g:render template="/userManagement/form" model="[user:user]" />

</layout:formPanel>


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
        $('#alertMessage').empty();
        $('.form-group').removeClass('has-error');
        var el;

        if(json.message) {
            el = jQuery("#messageStatus").html(json.message);
        }

        if(json.alert) {
            el = jQuery("#alertMessage").html(json.alert);
        }

        if (json.success) {
            // $('#userForm').clearForm();
            $('#userForm').resetForm();
        }
        else {
            $(json.errorList).each(function (index, value) {
                $("#formGroup_" + value).addClass("has-error");
            });
        }

        $('html,body').animate({scrollTop: $(el).offset().top - 100},'slow');


    }
</script>

</body>
</html>

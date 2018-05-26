<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
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


    <g:hasErrors bean="${this.registration}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.registration}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:uploadForm name="userForm" action="save">


        <g:set var="objectName" value="registration" />

        <fieldset class="form">




            <g:render template="/template/shared/uploadComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'multipartFile'
                      ]"
            />


            <g:render template="/template/shared/textComponent"

                      model="[
                              isRequired:true,
                              object:registration,
                              objectKey:objectName,
                              fieldName:'username',
                              fieldValue:registration?.username
                      ]"
            />


            <g:render template="/template/shared/passwordComponent"

                      model="[
                              isRequired:true,
                              object:registration,
                              objectKey:objectName,
                              fieldName:'password',
                              fieldValue:registration?.password
                      ]"
            />

            <g:render template="/template/shared/textComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'homepage',
                              fieldValue:registration?.homepage
                      ]"
            />


            <g:render template="/template/shared/textComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'applicationName',
                              fieldValue:registration?.applicationName
                      ]"
            />




            <g:render template="/template/shared/textComponent"

                      model="[
                              isRequired:true,
                              object:registration,
                              objectKey:objectName,
                              fieldName:'fullName',
                              fieldValue:registration?.fullName
                      ]"
            />


            <g:render template="/template/shared/textComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'bio',
                              fieldValue:registration?.bio
                      ]"
            />

            <g:render template="/template/shared/textComponent"

                      model="[
                              isRequired:true,
                              object:registration,
                              objectKey:objectName,
                              fieldName:'email',
                              fieldValue:registration?.email
                      ]"
            />


            <g:render template="/template/shared/textComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'timezone',
                              fieldValue:registration?.timezone
                      ]"
            />


            <g:render template="/template/shared/textComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'address',
                              fieldValue:registration?.address
                      ]"
            />
            <g:render template="/template/shared/textComponent"

                      model="[
                              object:registration,
                              objectKey:objectName,
                              fieldName:'salary',
                              fieldValue:registration?.salary
                      ]"
            />


            <g:render template="/template/shared/dateComponent"

                      model="[
                              isRequired:true,
                              object:registration,
                              objectKey:objectName,
                              fieldName:'dateOfBirth',
                              fieldValue:registration?.dateOfBirth
                      ]"
            />


            <g:render template="/template/shared/selectComponent"

                      model="[
                              isRequired:true,
                              object:registration,
                              objectKey:objectName,
                              labelFieldName:'country',
                              fieldName:'countryId',
                              fieldValue:registration?.countryId,
                              dataList:edu.training.Country.list(),
                              optionKey:'id',
                              optionValue:'name',
                      ]"
            />


        </fieldset>




        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:uploadForm>
</div>

</body>
</html>

<div class="fieldcontain ${isRequired?'required':''}">

   <g:render template="/template/shared/labelComponent"
             model="[isRequired:isRequired,
                     objectKey:objectKey,
                     fieldName:fieldName]"/>

    <g:passwordField name="${fieldName}" value="${fieldValue}" isRequired="${isRequired}"/>

</div>
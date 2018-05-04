<div class="fieldcontain ${isRequired?'required':''}">

    <g:render template="/template/shared/labelComponent"
              model="[isRequired:isRequired,
                      objectKey:objectKey,
                      fieldName:fieldName]"/>

    <g:datePicker name="${fieldName}" value="${fieldValue}" isRequired="${isRequired}"/>

</div>
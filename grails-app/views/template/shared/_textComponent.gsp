<g:set var="hasError"  value="${object?.errors?.allErrors?.findAll{it.field == fieldName}?.size() > 0}" />
<div class="fieldcontain ${isRequired?' required':''} ${hasError==true?' error':''} " >
   <g:render template="/template/shared/labelComponent"
             model="[isRequired:isRequired,
                     objectKey:objectKey,
                     fieldName:fieldName]"/>

    <g:textField name="${fieldName}" value="${fieldValue?:(object?."${fieldName}")}" isRequired="${isRequired}"/>

</div>
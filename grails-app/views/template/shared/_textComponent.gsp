<g:set var="hasError"  value="${object?.errors?.allErrors?.findAll{it.field == fieldName}?.size() > 0}" />


<div id="fieldcontain_${fieldRealName?:fieldName}" class="fieldcontain ${isRequired?' required':''} ${hasError==true?' error':''} " >
   <g:render template="/template/shared/labelComponent"
             model="[isRequired:isRequired,
                     objectKey:objectKey,
                     fieldName:fieldName]"/>

    <g:textField name="${fieldRealName?:fieldName}" value="${fieldValue?:(object?."${fieldName}")}" isRequired="${isRequired}" class="${clazz}"/>

</div>
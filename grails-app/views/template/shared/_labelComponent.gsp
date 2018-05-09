<label for="${fieldName}">
    <g:message code="${objectKey}.${fieldName}.label" default="${labelValue?:fieldName}"/>
    <g:if test="${isRequired}">
        <span class="required-indicator">*</span>
    </g:if>
</label>
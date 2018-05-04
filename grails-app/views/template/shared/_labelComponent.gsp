<label for="${fieldName}">
    <g:message code="${objectKey}.${fieldName}.label" default="${fieldName}"/>
    <g:if test="${isRequired}">
        <span class="required-indicator">*</span>
    </g:if>
</label>
<div class="fieldcontain ${isRequired?'required':''}">

    <g:render template="/template/shared/labelComponent"
              model="[isRequired:isRequired,
                      objectKey:objectKey,
                      fieldName:(labelFieldName?:fieldName)]"/>

    <g:select noSelection="['':message(code:'select.label',default: 'select')]" from="${dataList}"
              optionKey="${optionKey}"
              optionValue="${optionValue}"
              name="${fieldName}"
              value="${fieldValue}"
              isRequired="${isRequired}"
              isDisabled="${isDisabled}"
              isReadOnly="${isReadOnly}"/>

</div>
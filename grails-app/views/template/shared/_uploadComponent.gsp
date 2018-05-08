<g:set var="hasError"  value="${object?.errors?.allErrors?.findAll{it.field == fieldName}?.size() > 0}" />

<div id="fieldcontain_${fieldName}" class="fieldcontain ${isRequired?' required':''} ${hasError==true?' error':''} " >
    <g:render template="/template/shared/labelComponent"
              model="[isRequired:isRequired,
                      objectKey:objectKey,
                      fieldName:fieldName]"/>



    <input type="file" name="${fieldName}" id="${fieldName}" />


    <div id="imageView">

    </div>

</div>

<g:if test="${object?.id && object?."${fieldValueName}"}">
    <script type="text/javascript">
        var img = $("<img />").attr('src', '${createLink(controller: 'profile',action: 'loadImage')}/${object?.id}').on('load', function() {
                if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
                    alert('broken image!');
                } else {
                    $("#imageView").append(img);
                }
            });
    </script>
</g:if>
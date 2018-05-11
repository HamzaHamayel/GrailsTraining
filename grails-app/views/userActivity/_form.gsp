


<g:render template="/template/shared/autoComplete"
          model="[
                  controller:'user',
                  required:true,
                  label:message(code:'userActivity.user.label',default: 'user'),
                  object:userActivity,
                  fieldHiddenId:'userId',
                  fieldHiddenName:'user.id',
                  fieldTextName:'user',
                  fieldHiddenValue:userActivity?.user?.id,
                  fieldTextValue:userActivity?.user?.userId
          ]"
/>

<field:text name="activityName" required="true" object="${userActivity}"  label="${message(code:'userActivity.activityName.label',default: 'activityName')}" value="${userActivity?.activityName}" />

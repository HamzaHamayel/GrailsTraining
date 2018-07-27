
<g:applyLayout name="mainCustom" >

    <html>
    <head>
        <title>
            <g:layoutTitle default="list page"/>
        </title>

        <asset:stylesheet src="vendor/datatables-plugins/dataTables.bootstrap.css" />
        <asset:stylesheet src="vendor/datatables-responsive/dataTables.responsive.css" />
        <asset:stylesheet src="vendor/datetimepicker/css/bootstrap-datetimepicker.min.css" />

        <g:layoutHead/>

    </head>

    <body>

    <g:layoutBody/>

    <asset:javascript src="vendor/datatables/js/jquery.dataTables.min.js" />
    <asset:javascript src="vendor/datatables-plugins/dataTables.bootstrap.min.js" />
    <asset:javascript src="vendor/datatables-responsive/dataTables.responsive.js" />
    <asset:javascript src="vendor/mask/jquery.mask.min.js" />

    <asset:javascript src="vendor/datetimepicker/js/bootstrap-datetimepicker.js" />
    <asset:javascript src="vendor/datetimepicker/js/locales/bootstrap-datetimepicker.ar.js" />

    </body>

</g:applyLayout>


<script>
    jQuery(function($){
        $(".currencyInput").mask('0ZZZZZZZZZ.000', {translation:  {'Z': {pattern: /[0-9]/, optional: true}}});
        $(".integerInput").mask('0000000000');
        $(".mailInput").mask("A", {
            translation: {
                "A": { pattern: /[\w@\-.+]/, recursive: true }
            }
        });

        $('.dateInput').datetimepicker({
            language:  '${org.springframework.context.i18n.LocaleContextHolder.getLocale().language}',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            formatViewType:"date",
            format: "dd/mm/yyyy"
        });

    });
</script>

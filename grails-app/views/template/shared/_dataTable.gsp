<script type="text/javascript">
    _columns = new Array();
</script>
<table id="myTable">
    <thead>
    <tr>
        <g:each in="${columns}" var="column">
            <th>
                ${column?.value}
                <script type="text/javascript">
                    _columns.push({ "data": "${column?.key}" });
                </script>
            </th>
        </g:each>
        <th>
            Tools
        </th>
    </tr>
    </thead>
    <tbody></tbody>
</table>


<script type="text/javascript">
    _columns.push({
        "data": null,
        "bSortable": false,
        "bSearchable": false,
        "render": function (obj, data) {
            var link = "${createLink(action: 'show')}/" + obj.id;
            return "<a href='" + link + "'>show<a>";
        }
    });

    $(document).ready( function () {
        $('#myTable').DataTable( {
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url":  "${createLink(controller: controller,action: action)}",
                "type": "POST",
                "data": function ( d ) {
                    d.draw = d.draw;
                    d.sSearch = d.search.value;
                    d.offset = d.start;
                    d.max = d.length;
                    d.orderBy = d.columns[d.order[0]['column']].data;
                    d.dir = d.order[0]['dir'];
                }
            },
            "columns": _columns
        } );
    } );

</script>


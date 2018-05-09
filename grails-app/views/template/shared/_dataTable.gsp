<script type="text/javascript">
    _columns = new Array();
</script>
<table id="myTable">
    <thead>
    <tr>
        <g:each in="${columns}" var="column">
            <th>
                ${column?.value}
                ${column?.visible}
                <script type="text/javascript">
                    _columns.push({ "data": "${column?.key}","visible":${column?.visible?:'true'} });
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
        _myDataTable = $('#myTable').DataTable( {
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ajax": {
                "url":  "${createLink(controller: controller,action: action)}",
                "type": "POST",
                "data": function ( d ) {

                    var indexed_array = {};
                    var searchFormArray = $('#${searchFrom}').serializeArray();

                    $.map(searchFormArray, function(n, i){
                        indexed_array[n['name']] = n['value'];
                    });


                    // d.draw = d.draw;
                    // d.sSearch = d.search.value;
                    // d.offset = d.start;
                    // d.max = d.length;
                    // d.orderBy = d.columns[d.order[0]['column']].data;
                    // d.dir = d.order[0]['dir'];

                    indexed_array["draw"] = d.draw;
                    indexed_array["sSearch"] = d.search.value;
                    indexed_array["offset"] = d.start;
                    indexed_array["max"] = d.length;
                    indexed_array["orderBy"] = d.columns[d.order[0]['column']].data;
                    indexed_array["dir"] = d.order[0]['dir'];


                    return indexed_array;
                }
            },
            "columns": _columns
        } );
    } );

</script>


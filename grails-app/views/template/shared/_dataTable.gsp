<script type="text/javascript">
    _columns = new Array();
</script>


<div class="panel panel-default">
    <div class="panel-heading">
        ${title?:"Data List"}
    </div>
    <!-- /.panel-heading -->
    <div class="panel-body">

        <table id="myTable" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <g:each in="${columns}" var="column">
                    <th>
                        ${column?.value}
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

    </div>
</div>


<script type="text/javascript">
    _columns.push({
        "data": null,
        "bSortable": false,
        "bSearchable": false,
        "render": function (obj, data) {
            var idComp = "${idComp?:""}";
            var link = "${createLink(action: 'show')}";
            if(idComp == "") {
                link = link + "/" + obj.id;
            }else{
                <g:each in="${idComp}" var="com" status="idx">
                <g:if test="${idx == 0}">
                link = link + "?${com}="+obj['${com}'];
                </g:if>
                <g:else>
                link = link + "&${com}="+obj['${com}'];
                </g:else>
                </g:each>
            }
            return "<a href='" + link + "'>show<a>";
        }
    });

    $(document).ready( function () {
        _myDataTable = $('#myTable').DataTable( {


            "language":{
                "processing":"<g:message code="default.dataTable.processing" />",
                "loadingRecords":"<g:message code="default.dataTable.loadingRecords" />",
                "lengthMenu":"<g:message code="default.dataTable.lengthMenu" />",
                "zeroRecords":"<g:message code="default.dataTable.zeroRecords" />",
                "info":"<g:message code="default.dataTable.info" />",
                "infoEmpty":"<g:message code="default.dataTable.infoEmpty" />",
                "infoFiltered":"<g:message code="default.dataTable.infoFiltered" />",
                "infoPostFix":"<g:message code="default.dataTable.infoPostFix" />",
                "search":"<g:message code="default.dataTable.search" />",
                "url":"<g:message code="default.dataTable.url" />",
                "paginate":{
                    "first":"<g:message code="default.dataTable.paginate.first" />",
                    "previous":"<g:message code="default.dataTable.paginate.previous" />",
                    "next":"<g:message code="default.dataTable.paginate.next" />",
                    "last":"<g:message code="default.dataTable.paginate.last" />"
                }
            },

            "lengthMenu":[
                [10, 25, 50, 100, -1],
                [10, 25, 50, 100, "${message(code: 'default.dataTable.showAllRecords', default: 'All records')}"]
            ],


            "responsive": true,
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ajax": {
                "url":  "${createLink(controller: controller,action: action)}",
                "type": "POST",
                "data": function ( d ) {

                    var indexed_array = {};

                    <g:if test="${searchFrom}">
                    var searchFormArray = $('#${searchFrom}').serializeArray();

                    $.map(searchFormArray, function(n, i){
                        indexed_array[n['name']] = n['value'];
                    });
                    </g:if>

                    indexed_array["draw"] = d.draw;
                    indexed_array["sSearch"] = d.search.value;
                    indexed_array["offset"] = d.start;
                    indexed_array["max"] = d.length;
                    indexed_array["orderBy"] = d.columns[d.order[0]['column']].data;
                    indexed_array["dir"] = d.order[0]['dir'];


                    return indexed_array;
                }
            },
            "columns": _columns,
        } );
    } );

</script>


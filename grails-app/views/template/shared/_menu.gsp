<ul class="nav" id="side-menu">


    <!-- home -->
    <li>
        <g:link uri="/" >
            <i class="fa fa-dashboard fa-fw"></i> Home
        </g:link>
    </li>


    <!-- test -->
    <li>
        <g:link controller="test" action="list" >
            <i class="fa fa-try fa-fw"></i> Test
        </g:link>
    </li>

    <!-- user -->
    <li>
        <a href="#"><i class="fa fa-users fa-fw"></i> Users<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="user" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="user" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>



    <!-- profile -->
    <li>
        <a href="#"><i class="fa fa-user fa-fw"></i> Profiles<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="profile" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="profile" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>




    <!-- country -->
    <li>
        <a href="#"><i class="fa fa-home fa-fw"></i> Countries<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="country" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="country" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>





    <!-- transaction -->
    <li>
        <a href="#"><i class="fa fa-tasks fa-fw"></i> Transactions<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="transaction" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="transaction" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>






    <!-- post -->
    <li>
        <a href="#"><i class="fa fa-bullhorn fa-fw"></i> Posts<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="post" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="post" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>



    <!-- tag -->
    <li>
        <a href="#"><i class="fa fa-tags fa-fw"></i> Tags<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="post" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="post" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>


    <!-- tagPost -->
    <li>
        <a href="#"><i class="fa fa-bookmark fa-fw"></i> Post Tags<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="tagPost" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="tagPost" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>



    <!-- country -->
    <li>
        <a href="#"><i class="fa fa-user-plus fa-fw"></i> User Activity<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
            <li>
                <g:link controller="userActivity" action="list" >
                    <i class="fa fa-list" ></i>
                    List
                </g:link>
            </li>
            <li>
                <g:link controller="userActivity" action="create" >
                    <i class="fa fa-plus" ></i>
                    Create
                </g:link>

            </li>
        </ul>
    </li>




    %{--<li class="sidebar-search">--}%
    %{--<div class="input-group custom-search-form">--}%
    %{--<input type="text" class="form-control" placeholder="Search...">--}%
    %{--<span class="input-group-btn">--}%
    %{--<button class="btn btn-default" type="button">--}%
    %{--<i class="fa fa-search"></i>--}%
    %{--</button>--}%
    %{--</span>--}%
    %{--</div>--}%
    %{--<!-- /input-group -->--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span class="fa arrow"></span></a>--}%
    %{--<ul class="nav nav-second-level">--}%
    %{--<li>--}%
    %{--<a href="flot.html">Flot Charts</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="morris.html">Morris.js Charts</a>--}%
    %{--</li>--}%
    %{--</ul>--}%
    %{--<!-- /.nav-second-level -->--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="tables.html"><i class="fa fa-table fa-fw"></i> Tables</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="forms.html"><i class="fa fa-edit fa-fw"></i> Forms</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>--}%
    %{--<ul class="nav nav-second-level">--}%
    %{--<li>--}%
    %{--<a href="panels-wells.html">Panels and Wells</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="buttons.html">Buttons</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="notifications.html">Notifications</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="typography.html">Typography</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="icons.html"> Icons</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="grid.html">Grid</a>--}%
    %{--</li>--}%
    %{--</ul>--}%
    %{--<!-- /.nav-second-level -->--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>--}%
    %{--<ul class="nav nav-second-level">--}%
    %{--<li>--}%
    %{--<a href="#">Second Level Item</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#">Second Level Item</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#">Third Level <span class="fa arrow"></span></a>--}%
    %{--<ul class="nav nav-third-level">--}%
    %{--<li>--}%
    %{--<a href="#">Third Level Item</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#">Third Level Item</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#">Third Level Item</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#">Third Level Item</a>--}%
    %{--</li>--}%
    %{--</ul>--}%
    %{--<!-- /.nav-third-level -->--}%
    %{--</li>--}%
    %{--</ul>--}%
    %{--<!-- /.nav-second-level -->--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>--}%
    %{--<ul class="nav nav-second-level">--}%
    %{--<li>--}%
    %{--<a href="blank.html">Blank Page</a>--}%
    %{--</li>--}%
    %{--<li>--}%
    %{--<a href="login.html">Login Page</a>--}%
    %{--</li>--}%
    %{--</ul>--}%
    %{--<!-- /.nav-second-level -->--}%
    %{--</li>--}%
</ul>
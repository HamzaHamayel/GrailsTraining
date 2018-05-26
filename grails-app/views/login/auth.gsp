<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <s2ui:title messageCode='spring.security.ui.login.title'/>



    <asset:stylesheet src="vendor/bootstrap/css/bootstrap.min.css" />
    <asset:stylesheet src="vendor/metisMenu/metisMenu.min.css" />
    <asset:stylesheet src="dist/css/sb-admin-2.css" />
    <asset:stylesheet src="vendor/font-awesome/css/font-awesome.min.css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <asset:stylesheet src="vendor/ie8/html5shiv.js" />
    <asset:stylesheet src="vendor/ie8/respond.min.js" />
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">

                    <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform" autocomplete="off" role="form">
                        <fieldset>

                            <div class="form-group">
                                <input type="text" name="${usernameParameter ?: 'username'}" id="username" class="form-control"  placeholder="Username" autofocus>
                            </div>


                            <div class="form-group">
                                <input type="password" name="${passwordParameter ?: 'password'}" id="password" placeholder="Password" class="form-control" >
                            </div>

                            <div class="checkbox" id="remember_me_holder">
                                <label>
                                    <input type="checkbox"  name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" value="Remember Me" <g:if test='${hasCookie}'>checked="checked"</g:if>/><label for="remember_me"><g:message code='springSecurity.login.remember.me.label'/></label>
                                </label>
                            </div>


                            <button type="submit" id="submit"  class="btn btn-lg btn-success btn-block" value="${message(code: 'springSecurity.login.button')}"/>
                        </fieldset>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<asset:javascript src="vendor/jquery/jquery.min.js" />
<asset:javascript src="vendor/bootstrap/js/bootstrap.min.js" />
<asset:javascript src="dist/js/sb-admin-2.js" />

</body>

</html>

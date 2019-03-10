<%--
  Created by IntelliJ IDEA.
  User: yonut
  Date: 10/28/2018
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./bootstrap/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body id="LoginForm">
<div class="container text-center">
    <div style="text-align: center;margin:0 auto">
    <h1 class="form-heading">Login</h1>
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <p>Please enter your username and password</p>
            </div>
            <form id="Login" method="post" action="user">
                <div class="form-group text-center">
                    <input type="text" style="width: 200px;margin:0 auto"  class="form-control" id="inputUsername" name="username" placeholder="Username">
                </div>
                <div class="form-group text-center">
                    <input type="password" style="width: 200px;margin:0 auto" class="form-control" id="inputPassword" name="password" placeholder="Password">
                </div>
                <%--<div class="forgot">--%>
                    <%--<a href="reset.html">Forgot password?</a>--%>
                <%--</div>--%>
                <button type="submit"  class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
    </div>
</div>
</div>

</body>
</html>

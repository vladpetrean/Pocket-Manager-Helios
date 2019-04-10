<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<div style="background-color:#0066ff;height:70px">
  <button class="btn" style="float:left;margin-left:10px;margin-top:10px">LOGO</button>
  <button class="btn" style="float:right;margin-right:10px;margin-top:10px">LOGIN</button>
  <c:out value="${empty name ? 'name is empty or null' : 'name is NOT empty or null'}" />
</div>


<div class="container">
  <div class="row-sm-8 text-center">
    <div class="row-sm-2" style="height:300px">
      <h3>TEXT LOGIN</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
  </div>
  <div class="row text-center">
        <h3>FORMA LOGIN</h3>
      <form action="/index", method="post">
          First name:<br>
          <input type="text" name="userName">
          <br>
          Last name:<br>
          <input type="password" name="userPassword">
          <br><br>
          <input type="submit" value="Submit">
      </form>

        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
        <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
  </div>
</div>

</body>
</html>

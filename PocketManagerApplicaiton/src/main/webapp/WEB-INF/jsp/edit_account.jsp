<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<body bgcolor="FCD437">

<div class="container">
    <div class="row-sm-8 text-center">
        <h3>Welcome ${username}</h3>
    </div>

    <form action="/edit_account/${username}/${account_id}", method="post">
        Account name:<br>
        <input type="text" name="accountName" value=${accountName}>
        <br>
        Currency:<br>
        <input type="text" name="currency" value=${accountCurrency}>
        <br>
        Type:<br>
        <input type="text" name="type" value=${accountType}>
        <br>
        Amount:<br>
        <input type="text" name="amount" value=${accountAmount}>
        <br>
        <br><br>
        <input type="submit" value="Edit">
        <button><a href="/home/${username}">Back</a></button>
    </form>

    <div class="row text-center">


    </div>
</div>

</body>
</html>
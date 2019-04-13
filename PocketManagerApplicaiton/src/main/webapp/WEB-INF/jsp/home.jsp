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

    <form action="/create_account/${username}", method="post">
        Account name:<br>
        <input type="text" name="accountName">
        <br>
        Currency:<br>
        <input type="text" name="currency">
        <br>
        Type:<br>
        <input type="text" name="type">
        <br>
        Amount:<br>
        <input type="text" name="amount">
        <br>
        <br><br>
        <input type="submit" value="Create">
    </form>

    <div class="row text-center">
        <h3> List accounts</h3>
        <table class="table">
            <tr>
                <th align="center">Account name</th>
                <th align="center">Currency</th>
                <th align="center">Type</th>
                <th align="center">Amount</th>
                <th align="center">Edit</th>
                <th align="center">Delete</th>
            </tr>
            <c:forEach var="account" items="${accountArrayList}">
            <tr>
                <td>${account.accountName}</td>
                <td>${account.currency}</td>
                <td>${account.type}</td>
                <td>${account.amount}</td>
                <td style="color: blue"><a href="/edit_account/${username}/${account.id}">Edit</a></td>
                <td style="color: red"><a href="/delete_account/${username}/${account.id}">Delete</a></td>
            </tr>
            </c:forEach>
        </table>

    </div>
</div>

</body>
</html>

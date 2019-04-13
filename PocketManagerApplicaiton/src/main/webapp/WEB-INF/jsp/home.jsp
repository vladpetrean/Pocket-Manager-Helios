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
<body bgcolor="F9D665">

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
                <th>Account name</th>
                <th>Currency</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="account" items="${accountArrayList}">
            <tr>
                <td>${account.accountName}</td>
                <td>${account.currency}</td>
                <td>${account.type}</td>
                <td>${account.amount}</td>
                <td style="color: blue"><a href="edit_account/${username}/${account.id}"></a>Edit</td>
                <td style="color: red;"><a href="delete_account/${username}/${account.id}"></a>Delete</td>
            </tr>
            </c:forEach>
        </table>

    </div>
</div>

</body>
</html>

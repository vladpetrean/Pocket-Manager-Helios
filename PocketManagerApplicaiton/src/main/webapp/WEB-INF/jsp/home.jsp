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
<body>

<div class="container">
    <div class="row-sm-8 text-center">
        <div class="row-sm-2" style="height:300px">
            <h3>TEXT LOGIN</h3>
        </div>
    </div>

    <form action="/create_account/${username}", method="post">
        First name:<br>
        <input type="text" name="userName">
        <br>
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <div class="row text-center">
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
                <td>${account.id}</td>
                <td>${account.id}</td>

            </tr>
            </c:forEach>
        </table>

    </div>
</div>

</body>
</html>

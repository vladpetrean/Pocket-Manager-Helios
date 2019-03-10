<%@ page import="java.util.List" %>
  Created by IntelliJ IDEA.
  User: yonut
  Date: 10/29/2018
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User</title>
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./bootstrap/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        function getLocalTime() {
            var planeNo= $("#planeNo").val();
            var parameters;
            $.ajax({
                async: false,
                type: 'post',
                url: '/localhour',
                data:"id=" + planeNo,
                success:function (data) {
                    parameters = data.split("#");
                                 }
            });
            if(parameters.length <4 || planeNo===""){
                alert("Wrong plane number");
                return;
            }
            var depLat=parameters[0];
            var depLong=parameters[1];
            var arvCity=Number(parameters[2]);
            var arvLat=parameters[3];
            var arvLong=parameters[4];
            var departureOffset;
            var arrivalOffset;


            $.ajax({
                async:false,
                url:"https://maps.googleapis.com/maps/api/timezone/json?location="+arvLat+","+arvLong+"&timestamp="+
                    (Math.round((new Date().getTime())/1000)).toString() + "&key=AIzaSyCyMLAiKg-mitQ-M5_WETmme7HY6UaL_MQ",
            }).done(function(response){
                arrivalOffset = Number(response.rawOffset);
            });

            $.ajax({
                async:false,
                url:"https://maps.googleapis.com/maps/api/timezone/json?location="+depLat+","+depLong+"&timestamp="+
                    (Math.round((new Date().getTime())/1000)).toString() + "&key=AIzaSyCyMLAiKg-mitQ-M5_WETmme7HY6UaL_MQ",
            }).done(function(response){
                departureOffset = Number(response.rawOffset);

            });

            var offset = Number(arrivalOffset) - Number(departureOffset);
            var arrivalMillis = arvCity + offset * 1000;
            var arrivalTime = new Date(arrivalMillis);
            alert("Plane will arrive at "+arrivalTime.toString().substring(0,24));
        }

    </script>
</head>
<body>


<%
    String type= (String) request.getAttribute("user");
    if(type!=null && type.equals("USER")){

%>


<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light main-nav">
        <div class="container">
            <a class="navbar-brand" href="#">Welcome</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./login.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="form-group text-center">
    <div class="form-group text-center">
        <input type="number" style="width: 200px;margin:0 auto"  class="form-control" id="planeNo" name="planeNo" placeholder="Flight No">
    </div>
        <button type="button"  class="btn btn-dark" onclick="getLocalTime()">Local Time</button>
    </div>


    <div class="fl-table">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Flight Number</th>
                <th>Airplane Type</th>
                <th>Departure City</th>
                <th>Depart Hour</th>
                <th>Arrival City</th>
                <th>Arrival Hour</th>
                <%--<th>Action</th>--%>
            </tr>
            </thead>
            <tbody id="flightTable">
            <%
                @SuppressWarnings("unchecked")
                List<Flight> flights = (List<Flight>) request.getAttribute("flights");
                if(flights!=null && flights.size()>0){
                for (Flight flight:flights){
            %>


            <tr id="row<%=flight.getFlightNumber()%>>">
                <td><%=flight.getFlightNumber()%></td>
                <td><%=flight.getAirplaneType()%></td>
                <td><%=flight.getDepartureCity()%></td>
                <td><%=flight.getDepartureDate()%></td>
                <td><%=flight.getArrivalCity()%></td>
                <td><%=flight.getArrivalDate()%></td>
                <%--<td>--%>
                    <%--<button class="btn btn-primary" onclick="calcArrivaTime('<%=flight.getArrivalDate()%>',<%=departureCity.getLatitude()%>,<%=departureCity.getLongitude()%>,<%=arrivalCity.getLatitude()%>,<%=arrivalCity.getLongitude()%>)">--%>
                        <%--LocalHour</button> --%>
                <%--</td>--%>
            </tr>
            <%
                }
                }
            %>
            </tbody>

        </table>

    </div>
</header>
<%
    }else{


%>
<h3 class="text-center">Not Allowed </h3>
<h1 class="text-center">404</h1>


<%
    }
%>
</body>
</html>

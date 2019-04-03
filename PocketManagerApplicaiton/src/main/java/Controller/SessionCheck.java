package Controller;

import javax.servlet.http.HttpServletRequest;

public class SessionCheck {
    public static void setSessionUser(HttpServletRequest request, String attributeValue){

        request.setAttribute("user",attributeValue);  ////la apel attributeValue este ADMIN sau USER

    }
//    <%
//    String type= (String) request.getAttribute("user");
//    if(type!=null && type.equals("USER")){
//
//    }%>
//    --pagina--
//    <%
//}else{
//
//
//        %>
//    <h3 class="text-center">Not Allowed </h3>
//    <h1 class="text-center">404</h1>
//
//
//    <%
//        }
//        %>



    }

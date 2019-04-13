package Controller;

import Configuration.DatabaseOperation;
import Model.User;
import sun.security.smartcardio.SunPCSC;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

public class AuxiliaryFunction {

    public static boolean CheckPassword(String password, String username){
        ArrayList<User> user_account_list = DatabaseOperation.obtainUser();
        for(User user: user_account_list){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public static void setSessionUser(HttpServletRequest request, String attributeValue){

        request.setAttribute("user", attributeValue);

    }
    public static Boolean getSessionUser(HttpServletRequest request){

        if(request.getAttribute("user") != null){
            System.out.println("Gasit cookie");
            return true;
        }
        return false;

    }
}

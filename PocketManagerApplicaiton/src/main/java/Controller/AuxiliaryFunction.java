package Controller;

import Configuration.DatabaseOperation;
import Model.User;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

public class AuxiliaryFunction {

    public static boolean CheckPassword(String password, String username){
        ArrayList<User> user_account_list = DatabaseOperation.obtainUser();
        System.out.println("AJunge verificat parola");
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

        request.setAttribute("user", attributeValue);  ////la apel attributeValue este ADMIN sau USER

    }
}

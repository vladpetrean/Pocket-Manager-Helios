package Controller;

import Configuration.DatabaseOperation;
import Model.Account;
import Model.User;
import sun.security.smartcardio.SunPCSC;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
/* Mirela*/
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

    public static void setSessionUser(HttpServletResponse response, String attributeValue){
        response.addCookie(new Cookie("user", attributeValue));

    }
    public static Boolean getSessionUser(HttpServletRequest request){

        for(Cookie cookie: request.getCookies()){
            if(cookie.getName().equals("user")){
                System.out.println("Gasit");
                return true;
            }
        }
        return false;

    }

    public static Boolean verifyName(Account new_account, int user_id){
        ArrayList<Account> accountArrayList = DatabaseOperation.obtainUserAccount(user_id);
        for(Account account: accountArrayList){
            if(account.getAccountName().equals(new_account.getAccountName())){
                return false;
            }
        }
        return true;
    }
}
